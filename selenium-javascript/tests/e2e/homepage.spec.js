import { expect } from 'chai';
import DriverManager from '../../utilities/driver.js';
import HomePage from '../../pages/homepage.js';
import dotenv from 'dotenv';

dotenv.config();

describe('Homepage Test', () => {
  let driver;
  let homePage;

  beforeEach(async () => {
    driver = await DriverManager.getDriver();
    homePage = new HomePage(driver);
    await driver.get(process.env.ENV);
  });

  afterEach(async () => {
    await driver.quit();
  });

  it('should verify the title of the page', async () => {
    const title = await driver.getTitle();
    expect(title).to.contain('Selenium WebDriver');
  });

  it('should display all chapter cards', async () => {
    const chapter3 = await driver.findElement(homePage.chapter3Card);
    const chapter4 = await driver.findElement(homePage.chapter4Card);
    const chapter5 = await driver.findElement(homePage.chapter5Card);
    const chapter7 = await driver.findElement(homePage.chapter7Card);
    const chapter8 = await driver.findElement(homePage.chapter8Card);
    const chapter9 = await driver.findElement(homePage.chapter9Card);

    expect(await chapter3.isDisplayed()).to.be.true;
    expect(await chapter4.isDisplayed()).to.be.true;
    expect(await chapter5.isDisplayed()).to.be.true;
    expect(await chapter7.isDisplayed()).to.be.true;
    expect(await chapter8.isDisplayed()).to.be.true;
    expect(await chapter9.isDisplayed()).to.be.true;
  });
});
