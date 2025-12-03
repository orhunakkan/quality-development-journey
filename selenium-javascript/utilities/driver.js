import { Builder } from 'selenium-webdriver';
import chrome from 'selenium-webdriver/chrome.js';
import firefox from 'selenium-webdriver/firefox.js';
import edge from 'selenium-webdriver/edge.js';
import dotenv from 'dotenv';

dotenv.config();

class DriverManager {
    static async getDriver(browser = process.env.BROWSER || 'chrome') {
        const builder = new Builder().forBrowser(browser);

        switch (browser.toLowerCase()) {
            case 'chrome':
                builder.setChromeOptions(this.getChromeOptions());
                break;
            case 'firefox':
                builder.setFirefoxOptions(this.getFirefoxOptions());
                break;
            case 'edge':
                builder.setEdgeOptions(this.getEdgeOptions());
                break;
        }

        return await builder.build();
    }

    static getChromeOptions() {
        const options = new chrome.Options();

        if (process.env.HEADLESS === 'true') {
            options.addArguments('--headless=new');
        }

        // Common Chrome options for stability
        options.addArguments('--no-sandbox');
        options.addArguments('--disable-dev-shm-usage');
        options.addArguments('--disable-gpu');
        options.addArguments('--window-size=1920,1080');

        return options;
    }

    static getFirefoxOptions() {
        const options = new firefox.Options();

        if (process.env.HEADLESS === 'true') {
            options.addArguments('--headless');
        }

        options.addArguments('--width=1920');
        options.addArguments('--height=1080');

        return options;
    }

    static getEdgeOptions() {
        const options = new edge.Options();

        if (process.env.HEADLESS === 'true') {
            options.addArguments('--headless=new');
        }

        options.addArguments('--no-sandbox');
        options.addArguments('--disable-dev-shm-usage');
        options.addArguments('--disable-gpu');
        options.addArguments('--window-size=1920,1080');

        return options;
    }
}

export default DriverManager;
