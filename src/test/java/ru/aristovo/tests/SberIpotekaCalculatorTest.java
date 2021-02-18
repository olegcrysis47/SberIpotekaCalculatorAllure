package ru.aristovo.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aristovo.base.BaseTests;

import java.util.List;

public class SberIpotekaCalculatorTest extends BaseTests {

    @Test
    public void calculatorTest() {

        List<WebElement> menuSber =
                driver.findElements(By.xpath("//a[@class=' kitt-top-menu__link kitt-top-menu__link_first " +
                        "kitt-top-menu__link_icons kitt-top-menu__link_wide']"));
        for (WebElement w:menuSber) {
            if (w.getAttribute("aria-label").equalsIgnoreCase("ипотека")) {
                waitUtilElementToBeClickable(w);
                w.click();
                break;
            }
        }

        List<WebElement> subMenuSber =
                driver.findElements(By.xpath("//a[@class='kitt-top-menu__link kitt-top-menu__link_second']"));
        for (WebElement w:subMenuSber) {
            if (w.getText().equalsIgnoreCase("Ипотека на готовое жильё")) {
                waitUtilElementToBeClickable(w);
                w.click();
                break;
            }
        }

        WebElement titlePageIpoteka =
                driver.findElement(By.xpath("//div[@class='kit-col_xs_12 kit-col_md_0 kit-col_lg_6 " +
                        "kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']//h1"));
        Assert.assertEquals("Заголовок страницы не соответствует ожидаемому",
                "Ипотека от 7,3%* на готовые квартиры", titlePageIpoteka.getText());



        waitThread();


    }

    public void waitThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
