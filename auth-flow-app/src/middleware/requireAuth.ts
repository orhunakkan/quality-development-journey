import { Request, Response, NextFunction } from 'express';


export function requireAuth(req: Request, res: Response, next: NextFunction): void {
    if (!req.session.user) {
        res.status(401).send(`
            <!DOCTYPE html>
            <html>
            <head>
                <title>Not Logged In</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background: #f5f5f5;
                    }
                    .container {
                        text-align: center;
                        background: white;
                        padding: 40px;
                        border-radius: 8px;
                        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                    }
                    h1 {
                        color: #dc3545;
                        font-size: 2em;
                        margin: 0 0 20px 0;
                    }
                    a {
                        display: inline-block;
                        margin-top: 20px;
                        padding: 10px 20px;
                        background: #007bff;
                        color: white;
                        text-decoration: none;
                        border-radius: 5px;
                    }
                    a:hover {
                        background: #0056b3;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>YOU ARE NOT LOGGED IN!</h1>
                    <a href="/">Back to Home</a>
                </div>
            </body>
            </html>
        `);
        return;
    }
    next();
}
