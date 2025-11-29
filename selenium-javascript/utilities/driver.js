import { Builder } from 'selenium-webdriver';
import chrome from 'selenium-webdriver/chrome.js';
import edge from 'selenium-webdriver/edge.js';
import firefox from 'selenium-webdriver/firefox.js';

const DEFAULT_BROWSER = (process.env.BROWSER || 'chrome').toLowerCase();
const DEFAULT_HEADLESS = (process.env.HEADLESS || 'true').toLowerCase() === 'true';
const ENV_DRIVER_PATHS = {
    chrome: process.env.CHROME_DRIVER_PATH,
    edge: process.env.EDGE_DRIVER_PATH,
    firefox: process.env.FIREFOX_DRIVER_PATH,
};

const BROWSER_ALIASES = {
    chrome: 'chrome',
    edge: 'edge',
    microsoftedge: 'edge',
    firefox: 'firefox',
};

export async function createDriver({
    browser = DEFAULT_BROWSER,
    headless = DEFAULT_HEADLESS,
    implicitWaitMs = 5000,
    pageLoadTimeoutMs = 60000,
    windowSize = { width: 1920, height: 1080 },
    driverPath,
} = {}) {
    const normalizedBrowser = BROWSER_ALIASES[browser.toLowerCase()];
    if (!normalizedBrowser) {
        throw new Error(`Unsupported browser "${browser}". Use chrome, edge, or firefox.`);
    }

    const builder = new Builder();
    const servicePath = driverPath || ENV_DRIVER_PATHS[normalizedBrowser];

    const buildByBrowser = {
        chrome: () => {
            const options = new chrome.Options();
            if (headless) options.addArguments('--headless=new');
            options.addArguments(`--window-size=${windowSize.width},${windowSize.height}`);
            if (servicePath) builder.setChromeService(new chrome.ServiceBuilder(servicePath));
            builder.forBrowser('chrome').setChromeOptions(options);
        },
        edge: () => {
            const options = new edge.Options();
            if (headless) options.addArguments('--headless=new');
            options.addArguments(`--window-size=${windowSize.width},${windowSize.height}`);
            if (servicePath) builder.setEdgeService(new edge.ServiceBuilder(servicePath));
            builder.forBrowser('MicrosoftEdge').setEdgeOptions(options);
        },
        firefox: () => {
            const options = new firefox.Options();
            if (headless) options.addArguments('-headless');
            options.windowSize(windowSize);
            if (servicePath) builder.setFirefoxService(new firefox.ServiceBuilder(servicePath));
            builder.forBrowser('firefox').setFirefoxOptions(options);
        },
    };

    buildByBrowser[normalizedBrowser]();

    const driver = await builder.build();
    await driver.manage().setTimeouts({
        implicit: implicitWaitMs,
        pageLoad: pageLoadTimeoutMs,
    });

    // Patch driver.get to resolve '/' to the base URL
    const { getBaseUrl } = await import('./environments.js');
    const originalGet = driver.get.bind(driver);
    driver.get = async function (url) {
        if (url === '/') {
            url = getBaseUrl();
        }
        return originalGet(url);
    };
    return driver;
}

export async function quitDriver(driver) {
    if (driver) {
        await driver.quit();
    }
}
