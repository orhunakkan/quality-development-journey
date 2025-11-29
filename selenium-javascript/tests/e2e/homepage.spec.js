import { expect } from 'chai';
import { createDriver, quitDriver } from '../../utilities/driver.js';
import { getBaseUrl } from '../../utilities/environments.js';

const browsers = ['chrome', 'edge', 'firefox'];

for (const browser of browsers) {
    describe(`Homepage Tests - ${browser}`, function () {

        let driver;

        beforeEach(async () => {
            driver = await createDriver({ browser });
            await driver.get('/');
        });

        afterEach(async () => {
            await quitDriver(driver);
        });

        it('should load the homepage successfully', async () => {
            const title = await driver.getTitle();
            expect(title).to.equal('Hands-On Selenium WebDriver with Java');
        });
    });
}
