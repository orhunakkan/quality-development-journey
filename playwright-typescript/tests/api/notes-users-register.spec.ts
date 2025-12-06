import { test, expect } from '@playwright/test';

test.describe('Notes Users Register API', () => {

    const apiUrl = `${process.env.API_URL}/users/register`;

    test('should register a new user successfully', async ({ request }) => {
        const suffix = Date.now();
        const requestBody = {
            name: `testuser-${suffix}`,
            email: `testuser-${suffix}@example.com`,
            password: 'Test@1234'
        };

        const response = await request.post(apiUrl, {
            data: requestBody,
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const responseBody = await response.json();
        expect(response.status()).toBe(201);
        expect(responseBody).toHaveProperty('success', true);
        expect(responseBody).toHaveProperty('status', 201);
        expect(responseBody).toHaveProperty('message', 'User account created successfully');
        expect(responseBody).toHaveProperty('data');
        expect(responseBody.data).toHaveProperty('id');
        expect(typeof responseBody.data.id).toBe('string');
        expect(responseBody.data).toHaveProperty('name', requestBody.name);
        expect(responseBody.data).toHaveProperty('email', requestBody.email);
    });
});