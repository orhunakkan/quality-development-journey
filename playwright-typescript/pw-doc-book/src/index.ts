import { PlaywrightDocScraper } from './scraper.js';
import { PDFGenerator } from './pdf-generator.js';
import * as path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

async function main() {
    const startUrl = 'https://playwright.dev/docs/intro';
    const outputDir = path.join(__dirname, '..', 'output');
    const scrapedDataPath = path.join(outputDir, 'scraped-data.json');
    const pdfOutputPath = path.join(outputDir, 'playwright-documentation.pdf');

    console.log('🚀 Starting Playwright Documentation to PDF conversion...\n');

    try {
        // Step 1: Scrape all documentation pages
        console.log('📄 Step 1: Scraping documentation pages...');
        const scraper = new PlaywrightDocScraper();
        const pages = await scraper.scrapeAll(startUrl);
        await scraper.saveToFile(scrapedDataPath);
        console.log(`✅ Scraped ${pages.length} pages\n`);

        // Step 2: Generate PDF
        console.log('📚 Step 2: Generating PDF...');
        const pdfGenerator = new PDFGenerator();
        await pdfGenerator.generatePDF(pages, pdfOutputPath);
        console.log('✅ PDF generation complete\n');

        console.log('🎉 Success!');
        console.log(`📁 Scraped data: ${scrapedDataPath}`);
        console.log(`📖 PDF output: ${pdfOutputPath}`);
        console.log(`🌐 HTML preview: ${pdfOutputPath.replace('.pdf', '.html')}`);

    } catch (error) {
        console.error('❌ Error:', error);
        process.exit(1);
    }
}

main();
