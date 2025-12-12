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
    expect(await driver.getTitle()).to.equal('Hands-On Selenium WebDriver with Java');
  });

  it('should display all chapter cards', async () => {
    expect(await driver.findElement(homePage.chapter3Card).isDisplayed()).to.be.true;
    expect(await driver.findElement(homePage.chapter4Card).isDisplayed()).to.be.true;
    expect(await driver.findElement(homePage.chapter5Card).isDisplayed()).to.be.true;
    expect(await driver.findElement(homePage.chapter7Card).isDisplayed()).to.be.true;
    expect(await driver.findElement(homePage.chapter8Card).isDisplayed()).to.be.true;
    expect(await driver.findElement(homePage.chapter9Card).isDisplayed()).to.be.true;
  });
});
