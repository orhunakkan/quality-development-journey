import { chromium, Browser, Page } from 'playwright';
import * as fs from 'fs/promises';
import * as path from 'path';

interface DocPage {
    url: string;
    title: string;
    content: string;
    order: number;
}

export class PlaywrightDocScraper {
    private browser: Browser | null = null;
    private visitedUrls: Set<string> = new Set();
    private pages: DocPage[] = [];
    private baseUrl = 'https://playwright.dev';
    private docsPath = '/docs/';

    async initialize(): Promise<void> {
        this.browser = await chromium.launch({ headless: true });
    }

    async close(): Promise<void> {
        if (this.browser) {
            await this.browser.close();
        }
    }

    private isValidDocUrl(url: string): boolean {
        const urlObj = new URL(url, this.baseUrl);
        return (
            urlObj.hostname === 'playwright.dev' &&
            urlObj.pathname.startsWith(this.docsPath) &&
            !urlObj.pathname.includes('#')
        );
    }

    private normalizeUrl(url: string): string {
        const urlObj = new URL(url, this.baseUrl);
        urlObj.hash = '';
        return urlObj.href;
    }

    async scrapePage(url: string, order: number): Promise<void> {
        if (!this.browser) {
            throw new Error('Browser not initialized');
        }

        const normalizedUrl = this.normalizeUrl(url);

        if (this.visitedUrls.has(normalizedUrl)) {
            return;
        }

        console.log(`Scraping: ${normalizedUrl}`);
        this.visitedUrls.add(normalizedUrl);

        const page = await this.browser.newPage();

        try {
            await page.goto(normalizedUrl, { waitUntil: 'networkidle' });

            // Wait for content to load
            await page.waitForSelector('article', { timeout: 10000 });

            // Get page title
            const title = await page.title();

            // Get the main content including styles
            const content = await page.evaluate(() => {
                const article = document.querySelector('article');
                if (!article) return '';

                // Clone the article to avoid modifying the original
                const clone = article.cloneNode(true) as HTMLElement;

                return clone.outerHTML;
            });

            // Store page data
            this.pages.push({
                url: normalizedUrl,
                title,
                content,
                order
            });

            // Find all documentation links
            const links = await page.evaluate((docsPath) => {
                const anchors = Array.from(document.querySelectorAll('a[href]'));
                return anchors
                    .map(a => (a as HTMLAnchorElement).href)
                    .filter(href => href.includes(docsPath));
            }, this.docsPath);

            // Recursively scrape linked pages
            const uniqueLinks = [...new Set(links)];
            for (let i = 0; i < uniqueLinks.length; i++) {
                const link = uniqueLinks[i];
                if (this.isValidDocUrl(link)) {
                    await this.scrapePage(link, this.pages.length);
                }
            }

        } catch (error) {
            console.error(`Error scraping ${normalizedUrl}:`, error);
        } finally {
            await page.close();
        }
    }

    async scrapeAll(startUrl: string): Promise<DocPage[]> {
        await this.initialize();

        try {
            await this.scrapePage(startUrl, 0);
            console.log(`\nScraped ${this.pages.length} pages`);
            return this.pages;
        } finally {
            await this.close();
        }
    }

    async saveToFile(outputPath: string): Promise<void> {
        await fs.mkdir(path.dirname(outputPath), { recursive: true });
        await fs.writeFile(
            outputPath,
            JSON.stringify(this.pages, null, 2),
            'utf-8'
        );
        console.log(`Saved scraped data to ${outputPath}`);
    }

    getPages(): DocPage[] {
        return this.pages;
    }
}
