import 'express-session';
import { SessionUser } from '../auth/session';

declare module 'express-session' {
    interface SessionData {
        user?: SessionUser;
        state?: string;
        nonce?: string;
    }
}
