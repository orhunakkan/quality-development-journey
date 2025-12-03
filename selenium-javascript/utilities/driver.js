import { Builder } from 'selenium-webdriver';

export async function buildDriver() {
  const browser = (process.env.BROWSER || 'chrome').toLowerCase();
  switch (browser) {
    case 'chrome':
      return await new Builder().forBrowser('chrome').build();
    case 'edge':
      return await new Builder().forBrowser('MicrosoftEdge').build();
    case 'firefox':
      return await new Builder().forBrowser('firefox').build();
    default:
      throw new Error(`Unsupported browser: ${browser}`);
  }
}
