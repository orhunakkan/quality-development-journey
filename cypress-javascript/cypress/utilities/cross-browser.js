import cypress from 'cypress';

const spec = 'cypress/tests/e2e/**/*.cy.{js,jsx,ts,tsx}';
const browsers = ['chrome', 'edge', 'firefox'];

const results = [];

const isSuccessful = (runResult) => {
    if (!runResult) return false;
    if (runResult.totalFailed > 0) return false;
    if (runResult.failures > 0) return false;
    if (runResult.status && runResult.status !== 'finished') return false;
    return true;
};

for (const browser of browsers) {
    console.log(`\nRunning ${spec} in ${browser} (headless)...`);

    try {
        const runResult = await cypress.run({
            browser,
            headless: true,
            spec,
        });

        const success = isSuccessful(runResult);
        const status = success ? 'finished' : runResult?.status ?? 'unknown';

        results.push({
            browser,
            status,
            failed: runResult?.totalFailed ?? 0,
        });

        if (!success) {
            console.error(`Run failed for ${browser}`);
        }
    } catch (error) {
        console.error(`Error running ${browser}: ${error.message}`);
        results.push({
            browser,
            status: 'error',
            failed: 'n/a',
        });
    }
}

const hasFailures = results.some(
    ({ status, failed }) =>
        status === 'error' || status !== 'finished' || (typeof failed === 'number' && failed > 0),
);

console.log('\nSummary:');
for (const { browser, status, failed } of results) {
    console.log(`- ${browser}: status=${status}, failed=${failed}`);
}

if (hasFailures) {
    process.exitCode = 1;
}
