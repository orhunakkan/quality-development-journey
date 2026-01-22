export interface SessionUser {
    userId: string;
    username: string;
    email?: string;
    roles: string[];
    loginTime: number;
    idToken?: string;
}
