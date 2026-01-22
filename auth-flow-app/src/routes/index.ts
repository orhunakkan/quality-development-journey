import { Router, Request, Response } from 'express';
import { requireAuth } from '../middleware/requireAuth.js';
import { requireRole } from '../middleware/requireRole.js';


const publicRouter = Router();

// GET / - public home page
publicRouter.get('/', (req: Request, res: Response) => {
  const isLoggedIn = !!req.session.user;
  const username = req.session.user?.username || null;

  res.send(`
    <!DOCTYPE html>
    <html>
    <head>
      <title>Auth Flow App - Home</title>
      <style>
        body { font-family: Arial, sans-serif; margin: 40px; max-width: 600px; }
        a { display: inline-block; margin: 10px 0; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }
        a:hover { background: #0056b3; }
        .status { padding: 20px; background: #f8f9fa; border-radius: 5px; margin: 20px 0; }
      </style>
    </head>
    <body>
      <h1>Auth Flow App</h1>
      <div class="status">
        ${isLoggedIn
      ? `<p><strong>Status:</strong> Logged in as <em>${username}</em></p><p><strong>Roles:</strong> ${req.session.user?.roles?.join(', ') || 'None'}</p>`
      : '<p><strong>Status:</strong> Not logged in</p>'
    }
      </div>

      <h2>Navigation</h2>
      <ul>
        <li><a href="/dashboard">Dashboard</a> (protected)</li>
        <li><a href="/admin">Admin Panel</a> (admin only)</li>
        <li><a href="/api/me">Who am I (JSON)</a> (protected)</li>
      </ul>

      <h2>Actions</h2>
      <ul>
        ${!isLoggedIn
      ? '<li><a href="/auth/login">Login</a></li>'
      : '<li><a href="/auth/logout">Logout</a></li>'
    }
      </ul>
    </body>
    </html>
  `);
});

// GET /health - health check endpoint
publicRouter.get('/health', (req: Request, res: Response) => {
  res.json({ status: 'ok', timestamp: new Date().toISOString() });
});

const protectedRouter = Router();

// GET /dashboard - protected dashboard
protectedRouter.get(
  '/dashboard',
  requireAuth,
  (req: Request, res: Response) => {
    res.send(`
      <!DOCTYPE html>
      <html>
      <head>
        <title>Dashboard</title>
        <style>
          body { font-family: Arial, sans-serif; margin: 40px; }
          .info { padding: 20px; background: #e7f3ff; border-radius: 5px; }
          a { display: inline-block; margin: 10px 10px 10px 0; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }
        </style>
      </head>
      <body>
        <h1>Dashboard</h1>
        <div class="info">
          <p><strong>Welcome, ${req.session.user?.username}!</strong></p>
          <p><strong>Email:</strong> ${req.session.user?.email || 'N/A'}</p>
          <p><strong>User ID:</strong> ${req.session.user?.userId}</p>
          <p><strong>Roles:</strong> ${req.session.user?.roles?.join(', ') || 'None'}</p>
        </div>
        <p>
          <a href="/">Back to Home</a>
          <a href="/auth/logout">Logout</a>
        </p>
      </body>
      </html>
    `);
  }
);

// GET /admin - admin-only page
protectedRouter.get(
  '/admin',
  requireAuth,
  requireRole('admin'),
  (req: Request, res: Response) => {
    res.send(`
      <!DOCTYPE html>
      <html>
      <head>
        <title>Admin Panel</title>
        <style>
          body { font-family: Arial, sans-serif; margin: 40px; }
          .admin-info { padding: 20px; background: #ffe7e7; border-radius: 5px; }
          a { display: inline-block; margin: 10px 10px 10px 0; padding: 10px 20px; background: #28a745; color: white; text-decoration: none; border-radius: 5px; }
        </style>
      </head>
      <body>
        <h1>Admin Panel</h1>
        <div class="admin-info">
          <p><strong>Admin User:</strong> ${req.session.user?.username}</p>
          <p>This page is only accessible to users with the <code>admin</code> role.</p>
        </div>
        <p>
          <a href="/dashboard">Dashboard</a>
          <a href="/">Back to Home</a>
          <a href="/auth/logout">Logout</a>
        </p>
      </body>
      </html>
    `);
  }
);

const apiRouter = Router();

// GET /api/me - returns current user info (protected)
apiRouter.get('/me', requireAuth, (req: Request, res: Response) => {
  res.json({
    userId: req.session.user?.userId,
    username: req.session.user?.username,
    email: req.session.user?.email,
    roles: req.session.user?.roles,
    loginTime: req.session.user?.loginTime,
  });
});

export { publicRouter, protectedRouter, apiRouter };
