import crypto from 'crypto';

export function generateRandomString(length: number = 32): string {
    return crypto.randomBytes(length).toString('hex');
}

export function extractRolesFromToken(token: any): string[] {
    const realmRoles = token.realm_access?.roles || [];
    const clientRoles = token[`${token.aud}_roles`] || [];
    return Array.from(new Set([...realmRoles, ...clientRoles]));
}
