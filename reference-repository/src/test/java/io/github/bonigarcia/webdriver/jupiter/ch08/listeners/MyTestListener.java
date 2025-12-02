package io.github.bonigarcia.webdriver.jupiter.ch08.listeners;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.slf4j.Logger;

public class MyTestListener implements TestExecutionListener {

    static final Logger log = getLogger(lookup().lookupClass());

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        TestExecutionListener.super.executionStarted(testIdentifier);
        log.debug("Test execution started {}", testIdentifier.getDisplayName());
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        TestExecutionListener.super.executionSkipped(testIdentifier, reason);
        log.debug("Test execution skipped: {}", reason);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier,
            TestExecutionResult testExecutionResult) {
        TestExecutionListener.super.executionFinished(testIdentifier,
                testExecutionResult);
        log.debug("Test execution finished {}",
                testExecutionResult.getStatus());
    }

}
