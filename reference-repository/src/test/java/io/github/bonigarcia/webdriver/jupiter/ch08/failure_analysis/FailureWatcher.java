package io.github.bonigarcia.webdriver.jupiter.ch08.failure_analysis;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class FailureWatcher implements AfterTestExecutionCallback {

    FailureManager failureManager;

    public FailureWatcher(WebDriver driver) {
        failureManager = new FailureManager(driver);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            failureManager.takePngScreenshot(context.getDisplayName());
        }
    }

}
