import { expect } from 'chai';
import DriverManager from '../../utilities/driver.js';
import dotenv from 'dotenv';

dotenv.config();

describe('Homepage Test', function () {
  let driver;

  beforeEach(async () => {
    driver = await DriverManager.getDriver();
    await driver.get(process.env.ENV);
  });

  afterEach(async () => {
    await driver.quit();
  });

  it('should verify the title of the page', async () => {
    const title = await driver.getTitle();
    expect(title).to.contain('Selenium WebDriver');
  });
});
