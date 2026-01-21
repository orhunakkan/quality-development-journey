# Playwright Documentation to PDF

This application scrapes the official Playwright documentation and converts it into an interactive PDF book, preserving the dark theme styling and all hyperlinks.

## Features

- 🌐 **Complete Documentation Scraping**: Crawls all pages from the Playwright documentation
- 🎨 **Dark Theme Preserved**: Maintains the Playwright documentation's dark mode appearance
- 🔗 **Interactive Links**: All internal and external links are preserved in the PDF
- 📚 **Table of Contents**: Automatically generated TOC for easy navigation
- 💾 **Intermediate Caching**: Saves scraped data as JSON for faster regeneration

## Installation

Dependencies are managed in the parent `package.json`. Make sure you've run:

```bash
npm install
```

## Usage

### Run the complete process (scrape + generate PDF):

```bash
npm run doc-to-pdf
```

### Or run individual steps:

**Scrape documentation only:**
```bash
npm run doc-scrape
```

**Generate PDF from scraped data:**
```bash
npm run doc-generate
```

## Output

The application generates files in the `pw-doc-book/output/` directory:

- `scraped-data.json` - Raw scraped content from documentation pages
- `playwright-documentation.html` - HTML version for preview/debugging
- `playwright-documentation.pdf` - Final interactive PDF book

## Configuration

You can modify the scraper behavior in `src/scraper.ts`:

- **Start URL**: Change the landing page (default: `https://playwright.dev/docs/intro`)
- **Base URL**: Modify the documentation base URL
- **Filtering**: Adjust `isValidDocUrl()` to include/exclude specific pages

## Styling

The PDF styling is configured in `src/pdf-generator.ts` in the `getPlaywrightStyles()` method. It includes:

- Dark background (#1b1b1d)
- Syntax highlighting colors
- Code block styling
- Table formatting
- Link colors (#4fc3f7)

## How It Works

1. **Scraper** (`scraper.ts`):
   - Starts from the intro page
   - Extracts content and finds all documentation links
   - Recursively visits all unique documentation pages
   - Saves page content, title, and URL

2. **PDF Generator** (`pdf-generator.ts`):
   - Creates an HTML document with dark theme CSS
   - Generates a table of contents
   - Converts internal links to work in PDF
   - Uses Playwright's PDF generation with print backgrounds

## Technical Details

- **TypeScript**: Fully typed for better development experience
- **Playwright**: Used for both scraping and PDF generation
- **ESM Modules**: Uses ES module syntax
- **Headless Browser**: Runs efficiently in headless mode

## Limitations

- PDF links work best in modern PDF readers (Adobe Acrobat, Chrome, Edge)
- Some dynamic content may not be captured
- Very large documentation may take time to scrape

## Customization

To customize the appearance, edit the CSS in `src/pdf-generator.ts`. The styles are designed to match Playwright's documentation theme but can be adjusted to your preferences.

## License

ISC
