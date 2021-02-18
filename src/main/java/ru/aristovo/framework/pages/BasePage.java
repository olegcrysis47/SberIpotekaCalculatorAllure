package ru.aristovo.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aristovo.framework.managers.PageManager;
import ru.aristovo.framework.managers.TestPropManager;

import static ru.aristovo.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected PageManager app = PageManager.getPageManager();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    private final TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void fillField(WebElement element, String value) {
        element.sendKeys(Keys.CONTROL + "A");
        element.sendKeys(value);
        waitThread(1000);
    }

    protected void selectServ(WebElement element, String value) {
        if (!element.getAttribute("aria-checked").equalsIgnoreCase(value)) {
            element.click();
            waitThread(1000);
        }
    }

    protected void waitThread(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
