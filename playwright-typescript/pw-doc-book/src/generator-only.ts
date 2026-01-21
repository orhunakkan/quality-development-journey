import { PDFGenerator } from './pdf-generator.js';
import * as path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

async function main() {
    const outputDir = path.join(__dirname, '..', 'output');
    const scrapedDataPath = path.join(outputDir, 'scraped-data.json');
    const pdfOutputPath = path.join(outputDir, 'playwright-documentation.pdf');

    console.log('🚀 Generating PDF from scraped data...\n');

    try {
        const pdfGenerator = new PDFGenerator();
        await pdfGenerator.generateFromFile(scrapedDataPath, pdfOutputPath);

        console.log('\n✅ PDF generation complete!');
        console.log(`📖 PDF: ${pdfOutputPath}`);
        console.log(`🌐 HTML: ${pdfOutputPath.replace('.pdf', '.html')}`);

    } catch (error) {
        console.error('❌ Error:', error);
        if (error instanceof Error && error.message.includes('ENOENT')) {
            console.error('\n💡 Tip: Run scraper first with: npm run doc-scrape');
        }
        process.exit(1);
    }
}

main();
