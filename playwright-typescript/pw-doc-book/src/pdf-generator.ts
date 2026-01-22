import { chromium, Browser } from 'playwright';
import * as fs from 'fs/promises';
import * as path from 'path';

interface DocPage {
  url: string;
  title: string;
  content: string;
  order: number;
}

export class PDFGenerator {
  private browser: Browser | null = null;

  async initialize(): Promise<void> {
    this.browser = await chromium.launch({ headless: true });
  }

  async close(): Promise<void> {
    if (this.browser) {
      await this.browser.close();
    }
  }

  private getPlaywrightStyles(): string {
    return `
      <style>
        @page {
          size: A4;
          margin: 50px 50px;
          background-color: #1b1b1d;
        }

        * {
          box-sizing: border-box;
        }

        body {
          background-color: #1b1b1d;
          color: #d4d4d4;
          font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
          line-height: 1.6;
          margin: 0;
          padding: 0;
        }

        html {
          background-color: #1b1b1d;
        }

        article {
          max-width: 900px;
          margin: 0 auto;
          padding: 0;
        }

        .page-content {
          padding: 20px;
        }

        h1, h2, h3, h4, h5, h6 {
          color: #e4e4e4;
          margin-top: 24px;
          margin-bottom: 16px;
          font-weight: 600;
          line-height: 1.25;
        }

        h1 {
          font-size: 2em;
          border-bottom: 1px solid #404040;
          padding-bottom: 0.3em;
        }

        h2 {
          font-size: 1.5em;
          border-bottom: 1px solid #404040;
          padding-bottom: 0.3em;
        }

        h3 {
          font-size: 1.25em;
        }

        a {
          color: #4fc3f7;
          text-decoration: none;
        }

        a:hover {
          text-decoration: underline;
        }

        code {
          background-color: #2d2d30;
          color: #dcdcaa;
          padding: 0.2em 0.4em;
          border-radius: 3px;
          font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, monospace;
          font-size: 0.9em;
        }

        pre {
          background-color: #2d2d30 !important;
          border-radius: 6px;
          padding: 16px;
          overflow-x: auto;
          margin: 16px 0;
          color: #e8e8e8 !important;
        }

        pre code {
          background-color: #2d2d30 !important;
          padding: 0;
          font-size: 0.85em;
          color: #e8e8e8 !important;
        }

        pre code *,
        pre *,
        div[class*="codeBlock"] *,
        div[class*="code-block"] *,
        div[class*="language-"] * {
          color: #e8e8e8 !important;
        }

        div[class*="codeBlock"],
        div[class*="code-block"],
        div[class*="language-"] {
          background-color: #2d2d30 !important;
          color: #e8e8e8 !important;
        }

        .token.comment,
        .token.prolog,
        .token.doctype,
        .token.cdata {
          color: #6a9955 !important;
        }

        .token.punctuation {
          color: #d4d4d4 !important;
        }

        .token.property,
        .token.tag,
        .token.boolean,
        .token.number,
        .token.constant,
        .token.symbol,
        .token.deleted {
          color: #b5cea8 !important;
        }

        .token.selector,
        .token.attr-name,
        .token.string,
        .token.char,
        .token.builtin,
        .token.inserted {
          color: #ce9178 !important;
        }

        .token.operator,
        .token.entity,
        .token.url,
        .language-css .token.string,
        .style .token.string {
          color: #d4d4d4 !important;
        }

        .token.atrule,
        .token.attr-value,
        .token.keyword {
          color: #c586c0 !important;
        }

        .token.function,
        .token.class-name {
          color: #dcdcaa !important;
        }

        .token.regex,
        .token.important,
        .token.variable {
          color: #d16969 !important;
        }

        blockquote {
          border-left: 4px solid #4fc3f7;
          padding-left: 16px;
          margin-left: 0;
          color: #b0b0b0;
        }

        table {
          border-collapse: collapse;
          width: 100%;
          margin: 16px 0;
        }

        th, td {
          border: 1px solid #404040;
          padding: 8px 12px;
          text-align: left;
        }

        th {
          background-color: #2d2d30;
          font-weight: 600;
        }

        tr:nth-child(even) {
          background-color: #252526;
        }

        ul, ol {
          padding-left: 2em;
          margin: 16px 0;
        }

        li {
          margin: 4px 0;
        }

        img {
          max-width: 100%;
          height: auto;
          display: none;
        }

        .page-break {
          page-break-after: always;
          break-after: page;
        }

        .toc {
          margin: 0;
          padding: 0;
        }

        .toc h1 {
          text-align: center;
          margin: 0 0 10px 0;
          font-size: 1.8em;
        }

        .toc p {
          text-align: center;
          margin: 0 0 30px 0;
          font-size: 0.9em;
        }

        .toc-content {
          column-count: 3;
          column-gap: 25px;
          column-rule: 1px solid #404040;
        }

        .toc-section {
          break-inside: avoid;
          min-height: 0;
          page-break-inside: avoid;
        }

        .toc ul {
          list-style-type: none;
          padding-left: 0;
          margin: 0;
        }

        .toc li {
          margin: 3px 0;
          font-size: 0.92em;
          line-height: 1.3;
        }

        .toc li.api-label {
          margin-top: 16px;
          margin-bottom: 8px;
          padding-top: 8px;
          border-top: 1px solid #404040;
          font-size: 0.85em;
          color: #888;
          font-weight: 600;
          text-transform: uppercase;
          letter-spacing: 0.5px;
        }

        .toc li.api-section {
          margin: 2px 0;
        }

        .toc a {
          color: #4fc3f7;
          font-size: inherit;
          text-decoration: none;
        }

        .toc a:hover {
          text-decoration: underline;
        }

        .page-header {
          margin-bottom: 30px;
          padding-bottom: 10px;
          border-bottom: 2px solid #4fc3f7;
        }

        .page-url {
          color: #888;
          font-size: 0.9em;
          margin-top: 5px;
        }
      </style>
    `;
  }

  private generateTableOfContents(pages: DocPage[]): string {
    const sortedPages = [...pages].sort((a, b) => a.order - b.order);

    // Separate API and regular docs
    const apiPages = sortedPages.filter(p => p.url.includes('/docs/api/'));
    const regularPages = sortedPages.filter(p => !p.url.includes('/docs/api/'));

    // Function to clean title
    const cleanTitle = (title: string): string => {
      return title.replace(/\s*\|\s*Playwright\s*$/i, '').trim();
    };

    let tocHtml = `
      <div class="toc">
        <h1>Playwright Documentation</h1>
        <p>Interactive PDF Book</p>
        <div class="toc-content">
          <ul>
    `;

    // Add all regular docs
    if (regularPages.length > 0) {
      tocHtml += regularPages.map((page) => `
            <li>
              <a href="#page-${sortedPages.indexOf(page)}">${cleanTitle(page.title)}</a>
            </li>
          `).join('');
    }

    // Add API reference section
    if (apiPages.length > 0) {
      tocHtml += `
            <li class="api-label">API Reference</li>
      `;
      tocHtml += apiPages.map((page) => `
            <li class="api-section">
              <a href="#page-${sortedPages.indexOf(page)}">${cleanTitle(page.title)}</a>
            </li>
          `).join('');
    }

    tocHtml += `
          </ul>
        </div>
      </div>
      <div class="page-break"></div>
    `;

    return tocHtml;
  }

  private buildUrlToIdMap(pages: DocPage[]): Map<string, string> {
    const urlMap = new Map<string, string>();
    pages.forEach((page, index) => {
      const urlObj = new URL(page.url);
      const docPath = urlObj.pathname;
      urlMap.set(docPath, `page-${index}`);
    });
    return urlMap;
  }

  private processContent(content: string, pageUrl: string, pageId: string, urlToIdMap: Map<string, string>): string {
    let processed = content;

    // Remove all img tags
    processed = processed.replace(/<img[^>]*>/gi, '');

    // Remove navigation elements (home icons, breadcrumbs, etc)
    processed = processed.replace(/<nav[^>]*>[\s\S]*?<\/nav>/gi, '');

    // Remove "On this page" sections
    processed = processed.replace(/<aside[^>]*>[\s\S]*?<\/aside>/gi, '');

    // Remove divs with class containing "toc" or "sidebar" or "breadcrumb"
    processed = processed.replace(/<div[^>]*class="[^"]*(?:toc|sidebar|breadcrumb)[^"]*"[^>]*>[\s\S]*?<\/div>/gi, '');

    // Remove SVG elements (icons like home, info, warning icons)
    processed = processed.replace(/<svg[^>]*>[\s\S]*?<\/svg>/gi, '');

    // Remove all icon elements
    processed = processed.replace(/<(?:i|span|button)[^>]*(?:class="[^"]*(?:icon|note|warn|info)[^"]*"|aria-label)[^>]*>[\s\S]*?<\/(?:i|span|button)>/gi, '');

    // Remove script tags
    processed = processed.replace(/<script[^>]*>[\s\S]*?<\/script>/gi, '');

    // Convert internal doc links to PDF anchors
    processed = processed.replace(
      /href="\/docs\/([^"#]+)(#[^"]+)?"/g,
      (match, docPath, hash) => {
        const fullPath = `/docs/${docPath}`;
        const targetId = urlToIdMap.get(fullPath);
        if (targetId) {
          return `href="#${targetId}${hash || ''}"`;
        }
        // If not found in our pages, keep as external link
        return `href="https://playwright.dev/docs/${docPath}${hash || ''}"`;
      }
    );

    // Handle absolute playwright.dev links
    processed = processed.replace(
      /href="https:\/\/playwright\.dev\/docs\/([^"#]+)(#[^"]+)?"/g,
      (match, docPath, hash) => {
        const fullPath = `/docs/${docPath}`;
        const targetId = urlToIdMap.get(fullPath);
        if (targetId) {
          return `href="#${targetId}${hash || ''}"`;
        }
        return match;
      }
    );

    return `
      <div id="${pageId}" class="page-content">
        <div class="page-header">
          <div class="page-url">${pageUrl}</div>
        </div>
        ${processed}
      </div>
      <div class="page-break"></div>
    `;
  }

  async generatePDF(pages: DocPage[], outputPath: string): Promise<void> {
    if (!this.browser) {
      await this.initialize();
    }

    if (!this.browser) {
      throw new Error('Browser not initialized');
    }

    const page = await this.browser.newPage();

    try {
      // Sort pages by order
      const sortedPages = [...pages].sort((a, b) => a.order - b.order);

      // Build URL to ID mapping
      const urlToIdMap = this.buildUrlToIdMap(sortedPages);

      // Generate HTML content
      const htmlContent = `
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Playwright Documentation</title>
          ${this.getPlaywrightStyles()}
        </head>
        <body>
          ${this.generateTableOfContents(sortedPages)}
          ${sortedPages.map((docPage, index) =>
        this.processContent(docPage.content, docPage.url, `page-${index}`, urlToIdMap)
      ).join('\n')}
        </body>
        </html>
      `;

      // Save HTML for debugging
      const htmlPath = outputPath.replace('.pdf', '.html');
      await fs.writeFile(htmlPath, htmlContent, 'utf-8');
      console.log(`Generated HTML: ${htmlPath}`);

      // Set content and generate PDF
      await page.setContent(htmlContent, { waitUntil: 'networkidle' });

      // Ensure output directory exists
      await fs.mkdir(path.dirname(outputPath), { recursive: true });

      // Generate PDF with settings optimized for documentation
      await page.pdf({
        path: outputPath,
        format: 'A4',
        printBackground: true,
        margin: {
          top: '0px',
          right: '0px',
          bottom: '0px',
          left: '0px'
        },
        displayHeaderFooter: false,
        preferCSSPageSize: true
      });

      console.log(`Generated PDF: ${outputPath}`);
    } finally {
      await page.close();
    }
  }

  async generateFromFile(inputPath: string, outputPath: string): Promise<void> {
    const data = await fs.readFile(inputPath, 'utf-8');
    const pages: DocPage[] = JSON.parse(data);

    await this.initialize();
    await this.generatePDF(pages, outputPath);
    await this.close();
  }
}
