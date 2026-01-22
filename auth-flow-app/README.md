# Auth Flow App

A production-like enterprise OIDC authentication application demonstrating OAuth2 Authorization Code Flow with:

- **OpenID Connect (OIDC)** via Keycloak
- **HTTP-only session cookies** for app state (no tokens in localStorage)
- **PostgreSQL session storage** (sessions survive server restarts)
- **Role-Based Access Control (RBAC)** with Keycloak integration
- **CSRF protection** via state parameter
- **Token validation** using JWKS (signature, issuer, audience, expiration)
- **Secure cookie flags** (HttpOnly, SameSite, Secure)

## Prerequisites

- **Node.js 24.13.0** or later
- **PostgreSQL 13+** (local or Docker)
- **Docker & Docker Compose** (for Keycloak and PostgreSQL)
- **npm** or **yarn**

## Quick Start

### 1. Clone and Install Dependencies

```bash
cd auth-flow-app
npm install
```

### 2. Set Up Environment Variables

```bash
cp .env.example .env
```

Edit `.env` and update with your actual values. Key variables:

```env
PORT=3000
BASE_URL=http://localhost:3000
SESSION_SECRET=your-long-random-secret-change-this
DATABASE_URL=postgresql://postgres:postgres@localhost:5432/practice_sessions
KEYCLOAK_ISSUER_URL=http://localhost:8080/realms/practice-realm
OIDC_CLIENT_ID=practice-app
OIDC_CLIENT_SECRET=practice-app-secret-change-in-production
OIDC_REDIRECT_URI=http://localhost:3000/auth/callback
OIDC_POST_LOGOUT_REDIRECT_URI=http://localhost:3000/
SESSION_COOKIE_SECURE=false
SESSION_COOKIE_SAMESITE=lax
```

### 3. Start Keycloak and PostgreSQL

Using Docker Compose (recommended):

```bash
cd docker
docker-compose up -d
```

This starts:
- **PostgreSQL** on `localhost:5432` (user: postgres, password: postgres)
- **Keycloak** on `http://localhost:8080` with realm pre-imported

Wait ~10 seconds for services to be ready.

Verify Keycloak is running:
```bash
curl http://localhost:8080/realms/practice-realm/.well-known/openid-configuration
```

### 4. Create the Application Database

If PostgreSQL is running locally via Docker:

**On Windows:**
```bash
# First, ensure PostgreSQL is running and accessible
psql -h localhost -p 5432 -U postgres -c "CREATE DATABASE practice_sessions;"
```

**On macOS/Linux:**
```bash
scripts/init-db.sh
```

Or manually using `psql`:
```bash
psql -U postgres -h localhost
# In psql:
CREATE DATABASE practice_sessions;
```

### 5. Start the Application

```bash
npm run dev
```

App runs on `http://localhost:3000`

## Project Structure

```
auth-flow-app/
├── src/
│   ├── server.ts                 # Express app bootstrap
│   ├── config/
│   │   ├── env.ts                # Environment validation
│   │   └── oidc.ts               # OIDC client setup
│   ├── auth/
│   │   ├── session.ts            # Session type definitions
│   │   ├── tokenValidation.ts    # Token claim extraction & validation
│   │   └── routes.ts             # Auth endpoints (login, callback, logout)
│   ├── middleware/
│   │   ├── requireAuth.ts        # Auth guard middleware
│   │   └── requireRole.ts        # RBAC middleware
│   ├── routes/
│   │   └── index.ts              # Public & protected routes
│   └── utils/
│       └── logger.ts             # Logging utility
├── docker/
│   ├── docker-compose.yml        # Keycloak + Postgres compose file
│   └── keycloak/
│       └── realm-export.json     # Pre-configured realm with users & roles
├── scripts/
│   ├── init-db.sh               # Database init (Linux/macOS)
│   └── init-db.bat              # Database init (Windows)
├── package.json
├── tsconfig.json
├── .env.example
└── README.md
```

## API Routes

### Public Routes

- **GET `/`** - Home page with navigation
- **GET `/health`** - Health check endpoint

### Authentication Routes

- **GET `/auth/login`** - Initiates OIDC flow (redirects to Keycloak)
- **GET `/auth/callback`** - OIDC callback handler (processes auth code)
- **GET/POST `/auth/logout`** - Destroys session and logs out

### Protected Routes

- **GET `/dashboard`** - User dashboard (requires login)
- **GET `/admin`** - Admin panel (requires `admin` role)
- **GET `/api/me`** - Returns current user info as JSON (requires login)

## Authentication Flow

### Authorization Code Flow with PKCE

1. **User clicks "Login"** → redirected to `/auth/login`
2. **Server generates state & nonce** → stored in session
3. **Server redirects to Keycloak** with:
   - `response_type=code`
   - `client_id=practice-app`
   - `redirect_uri=http://localhost:3000/auth/callback`
   - `scope=openid profile email`
   - `state=<random>`
   - `nonce=<random>`

4. **User logs in at Keycloak** with credentials
5. **Keycloak redirects back** to `/auth/callback?code=...&state=...`
6. **Server validates state** (CSRF protection)
7. **Server exchanges code for tokens** using client secret
8. **Server validates ID token**:
   - Signature verification via JWKS
   - Issuer matches `KEYCLOAK_ISSUER_URL`
   - Audience matches `OIDC_CLIENT_ID`
   - Nonce matches stored value
   - Not expired

9. **Server creates session** with user data and roles
10. **Browser receives HTTP-only cookie** (no tokens in localStorage)
11. **Server regenerates session ID** (prevents session fixation)
12. **User redirected to `/dashboard`**

## Keycloak Configuration

### Realm: `practice-realm`

Pre-configured with:

#### Users

| Username | Password | Role(s) |
|----------|----------|---------|
| admin1 | admin1password | admin |
| manager1 | manager1password | manager |
| reader1 | reader1password | reader |

#### Roles

- `admin` - Full access including `/admin` page
- `manager` - Limited access
- `reader` - Read-only access

#### Client: `practice-app`

- **Client ID:** `practice-app`
- **Client Secret:** `practice-app-secret-change-in-production`
- **Access Type:** Confidential
- **Flow:** Authorization Code
- **Redirect URI:** `http://localhost:3000/auth/callback`
- **Web Origins:** `http://localhost:3000`
- **Post Logout URI:** `http://localhost:3000/`

### Access Keycloak Admin Console

1. Navigate to `http://localhost:8080`
2. Click "Administration Console"
3. Log in with `admin / admin`
4. Select realm `practice-realm`
5. Manage users, roles, clients

## Security Features

### 1. CSRF Protection
- State parameter generated server-side
- Validated on callback to prevent token interception

### 2. Token Validation
- Signature verified using Keycloak's JWKS endpoint
- Issuer validated to match Keycloak realm
- Audience validated to match app's client ID
- Expiration checked
- Nonce validated (prevents token reuse)

### 3. Session Fixation Prevention
- Session ID regenerated after successful login
- Old session destroyed

### 4. Secure Cookies
- `HttpOnly=true` - Not accessible to JavaScript
- `SameSite=lax` - Protected against CSRF
- `Secure=false` (dev) - Set to `true` for HTTPS
- `maxAge=3600000` - 1-hour session expiration

### 5. No Token Storage
- Access tokens never stored in localStorage
- Only session cookie used for request authentication
- Tokens discarded after session creation

### 6. PostgreSQL Session Storage
- Sessions persisted to database
- Survives server restarts
- Can be scaled across multiple server instances

## Development Workflow

### Start All Services

```bash
# Terminal 1: Start Keycloak + Postgres
cd docker
docker-compose up

# Terminal 2: Start app in dev mode
npm run dev
```

### Build for Production

```bash
npm run build
npm start
```

### Watch Mode

```bash
npm run dev
```

Uses `tsx` to watch and recompile TypeScript on changes.

## Testing the App

### 1. Test Public Access

```bash
curl http://localhost:3000/
```

Should return home page HTML.

### 2. Test Protected Route (Not Logged In)

```bash
curl -i http://localhost:3000/dashboard
```

Should redirect to `/auth/login`.

### 3. Manual User Login

1. Open `http://localhost:3000/`
2. Click "Login"
3. Log in as `reader1 / reader1password`
4. Should be redirected to `/dashboard`
5. Can access `/api/me` to see user info

### 4. Test RBAC

**As reader1 (no admin role):**
- ✅ Access `/dashboard`
- ❌ Access `/admin` (403 Forbidden)

**As admin1 (admin role):**
- ✅ Access `/dashboard`
- ✅ Access `/admin`

### 5. Verify Session Persistence

1. Log in to app
2. Stop Node server (Ctrl+C)
3. Restart Node server (`npm run dev`)
4. Session cookie still valid → stays logged in
5. Sessions stored in PostgreSQL table `session`

### 6. Verify No Tokens in localStorage

1. Open browser DevTools (F12)
2. Go to Application → Local Storage
3. Confirm no JWT or access tokens stored
4. Check Cookies tab → `connect.sid` is HttpOnly ✓

## Troubleshooting

### Issue: Cannot Connect to Keycloak

```
Error: connect ECONNREFUSED 127.0.0.1:8080
```

**Solution:**
```bash
# Check if Docker containers are running
docker ps

# Start containers if needed
cd docker
docker-compose up -d

# Wait 10 seconds for services to initialize
sleep 10

# Verify Keycloak health
curl http://localhost:8080/health
```

### Issue: Database Connection Error

```
Error: connect ECONNREFUSED 127.0.0.1:5432
```

**Solution:**
```bash
# Ensure PostgreSQL is running via Docker
cd docker
docker-compose up -d postgres

# Create database
psql -h localhost -U postgres -c "CREATE DATABASE practice_sessions;"

# Test connection
psql postgresql://postgres:postgres@localhost:5432/practice_sessions
```

### Issue: OIDC Token Validation Fails

```
Error: Invalid issuer: ...
```

**Solution:**
- Ensure `KEYCLOAK_ISSUER_URL` in `.env` matches Keycloak realm URL
- Check Keycloak admin console that realm `practice-realm` exists
- Verify Keycloak is accessible at the URL in `.env`

### Issue: Session Not Persisting

**Solution:**
- Check `DATABASE_URL` points to correct database
- Verify `practice_sessions` database exists
- Check PostgreSQL is running: `psql postgresql://postgres:postgres@localhost:5432/practice_sessions`
- Session table `session` should be auto-created on first login

### Issue: Callback Fails with "Invalid State"

```
Error: Invalid state parameter - possible CSRF attack
```

**Solution:**
- Clear browser cookies and try login again
- Ensure no multiple browser tabs/windows hitting the same app
- Check `OIDC_REDIRECT_URI` in `.env` matches Keycloak client config

## Environment Variables Reference

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | 3000 | Express server port |
| `BASE_URL` | http://localhost:3000 | App's public URL |
| `SESSION_SECRET` | - | Secret for signing session cookies (required, change in production) |
| `SESSION_COOKIE_SECURE` | false | Enable only over HTTPS |
| `SESSION_COOKIE_SAMESITE` | lax | CSRF protection: 'lax', 'strict', or 'none' |
| `DATABASE_URL` | - | PostgreSQL connection string |
| `KEYCLOAK_ISSUER_URL` | - | Keycloak realm URL |
| `OIDC_CLIENT_ID` | practice-app | Client ID in Keycloak |
| `OIDC_CLIENT_SECRET` | - | Client secret (required) |
| `OIDC_REDIRECT_URI` | http://localhost:3000/auth/callback | OAuth callback URL |
| `OIDC_POST_LOGOUT_REDIRECT_URI` | http://localhost:3000/ | Post-logout redirect |

## Production Upgrade Path

### HTTPS

1. Obtain SSL certificate (e.g., Let's Encrypt)
2. Set `SESSION_COOKIE_SECURE=true`
3. Configure Express with HTTPS:
   ```typescript
   import https from 'https';
   import fs from 'fs';
   
   const key = fs.readFileSync('path/to/key.pem');
   const cert = fs.readFileSync('path/to/cert.pem');
   https.createServer({ key, cert }, app).listen(PORT);
   ```

### Database

- Use managed PostgreSQL (AWS RDS, Google Cloud SQL, etc.)
- Enable SSL connections
- Set `DATABASE_URL` accordingly

### Keycloak

- Use production Keycloak deployment
- Configure HTTPS + certificate
- Update `KEYCLOAK_ISSUER_URL` to production domain
- Change client secret in Keycloak admin console

### Session Security

- Generate strong `SESSION_SECRET` (e.g., `openssl rand -base64 32`)
- Store in secure secret manager (HashiCorp Vault, AWS Secrets Manager)
- Reduce session `maxAge` if needed

### Deployment

- Build: `npm run build`
- Run: `npm start`
- Use process manager (PM2, systemd)
- Monitor logs and health endpoint

## API Examples

### Get Current User Info

```bash
curl -b "connect.sid=<your-session-cookie>" http://localhost:3000/api/me
```

Response:
```json
{
  "userId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "username": "admin1",
  "email": "admin1@example.com",
  "roles": ["admin"],
  "loginTime": 1705920000000
}
```

### Health Check

```bash
curl http://localhost:3000/health
```

Response:
```json
{
  "status": "ok",
  "timestamp": "2024-01-22T10:00:00.000Z"
}
```

## License

MIT

## Support

For issues or questions:
1. Check Keycloak logs: `docker logs practice-keycloak`
2. Check app logs: Review console output from `npm run dev`
3. Check PostgreSQL: `docker logs practice-postgres`
4. Review error messages in browser console
