import { Builder } from 'selenium-webdriver';
import { expect } from 'chai';

describe('Homepage Test', function() {
    let driver;

    beforeEach(async () => {
        driver = await new Builder().forBrowser('chrome').build();
        await driver.get('https://bonigarcia.dev/selenium-webdriver-java/');
    });

    afterEach(async () => {
        await driver.quit();
    });

    it('should verify the title of the page', async () => {
        const title = await driver.getTitle();
        expect(title).to.contain('Selenium WebDriver');
    });
});
