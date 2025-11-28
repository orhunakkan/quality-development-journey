import { expect } from 'chai';
import { createDriver, quitDriver } from '../../utilities/driver.js';

const browsers = ['chrome', 'edge', 'firefox'];

for (const browser of browsers) {
    describe(`Homepage Tests - ${browser}`, function () {
        this.timeout(60000);

        let driver;

        before(async () => {
            driver = await createDriver({ browser });
        });

        after(async () => {
            await quitDriver(driver);
        });

        it('should load the homepage successfully', async () => {
            await driver.get('https://bonigarcia.dev/selenium-webdriver-java/index.html');
            const title = await driver.getTitle();
            expect(title).to.equal('Hands-On Selenium WebDriver with Java');
        });
    });
}
