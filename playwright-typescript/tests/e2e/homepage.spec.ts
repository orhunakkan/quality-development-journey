import { expect, test } from '@playwright/test';

test.describe('Homepage Tests', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('');
  });

  test('should display the correct title', async ({ page }) => {
    await expect(page).toHaveTitle('Hands-On Selenium WebDriver with Java');
  });
});
