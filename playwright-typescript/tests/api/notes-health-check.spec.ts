import { test, expect } from '@playwright/test';

test.describe('Notes Health Check API', () => {

  const apiUrl = 'https://practice.expandtesting.com/notes/api/health-check';

  test('should return a successful health check response', async ({ request }) => {
    const response = await request.get(apiUrl);
    expect(response.status()).toBe(200);
    const responseBody = await response.json();
    expect(responseBody).toHaveProperty('success', true);
    expect(responseBody).toHaveProperty('message', 'Notes API is Running');
  });
});