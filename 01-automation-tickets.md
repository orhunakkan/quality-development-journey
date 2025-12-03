# Web UI Automation – Tool-Agnostic Tickets

These tickets are derived from a small set of browser automation examples but are written to be **independent of any specific automation tool or language**. You should be able to implement them using Selenium, Playwright, Cypress, WebdriverIO, Puppeteer, or any other UI automation stack.

---

## Ticket 1 – Verify basic browser metadata for the documentation home page

### Goal

Ensure the automation framework can open the documentation site and validate basic browser metadata:

- Page title
- Current URL
- Page source (high-level sanity check)

This maps to the behavior of the original "basic methods" test.  

### Functional behavior

1. Start a **fresh browser session**.
2. Navigate to the documentation URL (system under test – SUT), e.g.:
   - `https://bonigarcia.dev/selenium-webdriver-java/`
3. Validate:
   - The **page title** is exactly `"Hands-On Selenium WebDriver with Java"`.
   - The **current URL** equals the URL you asked the browser to open (no redirects that change it unexpectedly).
   - The **page source** (raw HTML) contains the closing HTML tag `</html>` (case-insensitive).

### Acceptance criteria

- [ ] The test opens the configured documentation URL in a fresh browser session.
- [ ] The test asserts the title **exactly matches** `"Hands-On Selenium WebDriver with Java"`.
- [ ] The test asserts the current URL is equal to the configured documentation URL.
- [ ] The test asserts the full page source includes a closing `</html>` tag, ignoring case.
- [ ] The browser is properly closed/terminated at the end of the test, even if an assertion fails.

### Implementation hints (tool-agnostic)

- Provide a **configurable SUT URL** (e.g., environment variable, config file, or test data file) instead of hard-coding it.
- Use your tool’s built-in APIs for:
  - Getting the page title.
  - Getting the current URL.
  - Getting the page source / DOM content.
- Make sure the test **fails clearly** (good assertion messages) when any of the expected values do not match.
- Ensure browser cleanup is done in a framework-specific “after each test” hook (e.g., `afterEach`, `@AfterEach`, `afterAll` with per-test session, etc.).

---

## Ticket 2 – Retrieve and log a browser/session identifier

### Goal

Expose and verify a **unique identifier for the running browser session**, and log it for debugging or traceability.

This maps to the original test that obtains and logs a WebDriver session ID.  

### Functional behavior

1. Start a fresh browser session.
2. Navigate to the documentation URL (same as Ticket 1).
3. Retrieve some **unique identifier** that represents the current browser session or context. Examples:
   - WebDriver `sessionId`
   - Playwright browser context ID (if available)
   - A custom-generated run ID that links to logs/traces (if your tool does not expose a session ID directly)
4. Assert that this identifier is **not null / not empty**.
5. Log the identifier in your framework’s logging mechanism at debug-level (or equivalent).

### Acceptance criteria

- [ ] The test runs against the same documentation URL as Ticket 1.
- [ ] The test retrieves a unique session or context identifier (framework-specific).
- [ ] The identifier is validated to be non-null / non-empty.
- [ ] The identifier is logged with a clear message (e.g., `"The session ID is <value>"`).
- [ ] Browser session is closed at the end of the test.

### Implementation hints (tool-agnostic)

- If your tool does **not** expose a native session ID:
  - Generate a UUID or timestamp-based run ID at the start of the test.
  - Store it in your test context/logs as the “session identifier” for tracing.
- Integrate with your logging strategy:
  - Console logs (stdout) for local/dev runs.
  - Structured logs if running in CI (include session ID as a field).

---

## Ticket 3 – Minimal smoke test against the documentation site

### Goal

Create a **small, fast smoke test** that ensures the documentation site is basically up and serving a page with a reasonable title.

This corresponds to an original test that simply checks that the title contains a known keyword.  

### Functional behavior

1. Start a fresh browser session.
2. Navigate to the documentation URL.
3. Get the page title.
4. Validate that the title **contains** the word `"Selenium WebDriver"` (case-insensitive match is acceptable).
5. Close the browser.

### Acceptance criteria

- [ ] Smoke test navigates to the same documentation URL as Tickets 1–2.
- [ ] Smoke test asserts that the title contains the substring `"Selenium WebDriver"`.
- [ ] The test is fast (suitable for running in CI on every commit).
- [ ] Proper browser cleanup is implemented.
- [ ] If the title does not contain the expected substring, the failure message clearly explains what was expected vs what was observed.

### Implementation hints (tool-agnostic)

- Reuse any **driver/browser setup helper** you create for Ticket 1.
- Keep the test minimal—no extra waits or page interactions unless strictly necessary.
- Optionally structure this as a separate **smoke test suite** that can be run independently in CI.

---

## Ticket 4 – Multi-browser support via a prioritized “builder/factory”

### Goal

Create a **browser factory / builder mechanism** that can try multiple browser types (e.g., Safari, Chrome, Firefox) in a priority order and return the first one that is available in the current environment.

This maps to a skeleton test that builds a driver with Safari as the primary option and Chrome as an alternative.  

### Functional behavior

1. Implement a **browser factory** (or builder) that:
   - Accepts a prioritized list of browser types (e.g., `["safari", "chrome"]`).
   - Attempts to start a session with the first browser type.
   - If the first option is not available on the current machine/OS, it automatically falls back to the next option in the list.
2. Write a test that:
   - Uses this factory to obtain a browser session with the configured preference order.
   - Navigates to `https://example.com/`.
   - Asserts that the page title is **not empty**.
3. Ensure the test is clearly marked as **environment-dependent** (e.g., Safari may not exist on non-macOS systems).

### Acceptance criteria

- [ ] A reusable **browser factory/builder** is implemented.
- [ ] The factory supports **at least two** browser types with a clear priority order.
- [ ] When the first browser is not available, the factory tries the next one and documents the fallback behavior.
- [ ] The test navigates to `https://example.com/` and asserts that the title is not empty.
- [ ] The test can be conditionally enabled/disabled or skipped in environments where no prioritized browser is available.
- [ ] Browser session is closed even if browser creation or navigation fails.

### Implementation hints (tool-agnostic)

- Represent browser type as a **string or enum** (e.g., `"chrome"`, `"firefox"`, `"safari"`).
- In your factory:
  - Try to launch each browser in order.
  - Catch/handle “browser not installed / not supported” errors.
  - Move to the next option until one succeeds, or fail with a clear error if none are available.
- Consider exposing factory configuration via:
  - Environment variables (e.g., `BROWSER_PRIORITY=safari,chrome`).
  - Config file or test runner options.
- In tools like Playwright or Cypress:
  - You can emulate a similar pattern by configuring the test matrix / project list or CLI flags, while still keeping the idea of a “preferred browser list”.

---

## Ticket 5 – Single-browser builder/factory with a generic smoke test

### Goal

Build a **simple, reusable builder/factory** for creating a single browser type with customizable options, and use it within a smoke test against a basic site.

This is inspired by a test that builds a single browser instance (e.g., Chrome) and navigates to `https://example.com/`.  

### Functional behavior

1. Implement a **builder or factory** that:
   - Creates a single browser instance for a chosen browser type (e.g., `"chrome"`, `"firefox"`, `"edge"`).
   - Allows passing basic configuration options (headless vs headed, window size, etc.).
2. Write a test that:
   - Uses the builder to create a browser instance (choose one default browser, e.g. Chrome-equivalent).
   - Navigates to `https://example.com/`.
   - Asserts that the page title is **not empty**.
3. Ensure that the builder is reusable across multiple future tests.

### Acceptance criteria

- [ ] A single-browser builder/factory function/class is implemented.
- [ ] The builder exposes a minimal configuration API (e.g., headless mode, viewport size).
- [ ] The builder is used in a test which:
  - Navigates to `https://example.com/`.
  - Asserts that the title is non-empty.
- [ ] The browser is closed at the end of the test.
- [ ] The builder can be easily extended in future (e.g., adding more configuration without breaking existing tests).

### Implementation hints (tool-agnostic)

- In code terms, think of:
  - A function like `createBrowser(options)` that returns a ready-to-use browser/page/context.
  - Or a class like `BrowserBuilder` with methods like `.withHeadless(true)` / `.build()`.
- Centralize browser creation in this builder/factory rather than duplicating setup logic in every test.
- In tools that already provide factories (e.g., Playwright test fixtures, Cypress `beforeEach` hooks), your “builder” may be a thin abstraction over those constructs.

---

## How to use these tickets

1. **Pick your UI automation tool** (Selenium, Playwright, Cypress, etc.).
2. For each ticket:
   - Implement the described behavior in your framework.
   - Keep the naming, structure, and configuration consistent across tickets so you can reuse components.
3. Once all tickets are complete, you will have:
   - Basic browser metadata validation.
   - A way to track/log browser sessions.
   - A small smoke suite for your documentation site.
   - A flexible browser factory/builder that supports multiple browsers and configuration options.
