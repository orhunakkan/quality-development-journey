package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExpandTestingInputTestPage {

    @FindBy(id = "btn-display-inputs")
    private WebElement displayInputsButton;

    @FindBy(id = "btn-clear-inputs")
    private WebElement clearButton;

    public ExpandTestingInputTestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDisplayInputsButton() {
        return displayInputsButton;
    }

    public WebElement getClearButton() {
        return clearButton;
    }
}