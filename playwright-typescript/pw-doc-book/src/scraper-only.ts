import { PlaywrightDocScraper } from './scraper.js';
import * as path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

async function main() {
    const startUrl = 'https://playwright.dev/docs/intro';
    const outputDir = path.join(__dirname, '..', 'output');
    const scrapedDataPath = path.join(outputDir, 'scraped-data.json');

    console.log('🚀 Scraping Playwright Documentation...\n');

    try {
        const scraper = new PlaywrightDocScraper();
        const pages = await scraper.scrapeAll(startUrl);
        await scraper.saveToFile(scrapedDataPath);

        console.log('\n✅ Scraping complete!');
        console.log(`📁 Output: ${scrapedDataPath}`);
        console.log(`📄 Total pages: ${pages.length}`);

    } catch (error) {
        console.error('❌ Error:', error);
        process.exit(1);
    }
}

main();
