# Playwright Documentation to PDF

This application scrapes the official Playwright documentation and converts it into an interactive PDF book, preserving the dark theme styling and all hyperlinks.

## Features

- 🌐 **Complete Documentation Scraping**: Crawls all pages from the Playwright documentation (139 pages)
- 🎨 **Dark Theme Preserved**: Maintains the Playwright documentation's dark mode appearance with improved code visibility
- 🔗 **Interactive Links**: All internal and external links are preserved and working in the PDF
- 📚 **Smart Table of Contents**: 3-column layout with automatic separation of regular docs and API reference
- 🧹 **Clean Content**: Removes navigation elements, broken images, and unnecessary icons
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

- Dark background (#1b1b1d) with proper book margins (50px)
- Enhanced syntax highlighting colors for better code readability
- Code block styling with light text for visibility
- Table formatting optimized for dark theme
- Link colors (#4fc3f7) that work both internally and externally
- 3-column table of contents with visual column separators

## How It Works

1. **Scraper** (`scraper.ts`):
   - Starts from the intro page (https://playwright.dev/docs/intro)
   - Extracts HTML content and finds all documentation links
   - Recursively visits all unique documentation pages
   - Saves page content, title, and URL in order

2. **Content Processing** (`pdf-generator.ts`):
   - Removes all images, navigation elements, and unnecessary icons
   - Cleans page titles (removes "| Playwright" suffix)
   - Converts internal doc links to work as PDF anchors
   - Preserves external links

3. **PDF Generation** (`pdf-generator.ts`):
   - Creates a 3-column table of contents with API reference distinction
   - Applies Playwright's dark theme styling with enhanced readability
   - Generates PDF with proper book margins and syntax highlighting
   - Uses Playwright's PDF generation with print backgrounds

## Technical Details

- **TypeScript**: Fully typed for better development experience
- **Playwright**: Used for both scraping and PDF generation
- **ESM Modules**: Uses ES module syntax
- **Headless Browser**: Runs efficiently in headless mode

## Limitations

- PDF links work best in modern PDF readers (Adobe Acrobat, Chrome, Edge)
- Very large documentation may take time to scrape (typically a few minutes for 139 pages)
- Some dynamically-loaded content may not be captured
- External links in PDF open in new tabs/windows depending on PDF reader

## Customization

To customize the appearance, edit the CSS in `src/pdf-generator.ts`. The styles are designed to match Playwright's documentation theme but can be adjusted to your preferences.

## License

ISC
