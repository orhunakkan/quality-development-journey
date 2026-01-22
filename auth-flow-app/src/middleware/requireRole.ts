import { Request, Response, NextFunction } from 'express';

export function requireRole(...allowedRoles: string[]) {
    return (req: Request, res: Response, next: NextFunction): void => {
        if (!req.session.user) {
            res.redirect('/auth/login');
            return;
        }

        const userRoles = req.session.user.roles || [];
        const hasRole = allowedRoles.some((role) => userRoles.includes(role));

        if (!hasRole) {
            res.status(403).send(`
        <!DOCTYPE html>
        <html>
        <head><title>Access Denied</title></head>
        <body>
          <h1>403 - Access Denied</h1>
          <p>You do not have permission to access this resource.</p>
          <a href="/">Back to Home</a>
        </body>
        </html>
      `);
            return;
        }

        next();
    };
}
