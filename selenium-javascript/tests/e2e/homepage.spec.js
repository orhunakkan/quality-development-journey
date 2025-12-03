import { expect } from 'chai';
import { buildDriver } from '../../utilities/driver.js';
import { getBaseUrl } from '../../utilities/environments.js';

const browser = (process.env.BROWSER || 'chrome').toLowerCase();

describe(`Homepage Tests - ${browser}`, function () {

    let driver;

    beforeEach(async () => {
        driver = await buildDriver();
        await driver.get(getBaseUrl());
    });

    afterEach(async () => {
        if (driver) await driver.quit();
    });

    it('should load the homepage successfully', async () => {
        const title = await driver.getTitle();
        expect(title).to.equal('Hands-On Selenium WebDriver with Java');
    });
});
