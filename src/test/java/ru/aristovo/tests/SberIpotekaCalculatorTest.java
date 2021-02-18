package ru.aristovo.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

        WebElement titleCalculateIpoteka = driver.findElement(By.xpath("//h2[contains(.,'Рассчитайте ипотеку')]"));
        scrollToElementJs(titleCalculateIpoteka);

        driver.switchTo().frame("iFrameResizer0");

        WebElement price = driver.findElement(By.xpath("//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Стоимость недвижимости')]//input"));
        price.sendKeys(Keys.CONTROL + "A");
        price.sendKeys("5 180 000");
        Assert.assertEquals("В поле СТОИМОСТЬ НЕДВИЖИМОСТИ введена неверная сумма",
                "5 180 000", price.getAttribute("value"));

        WebElement firstPay = driver.findElement(By.xpath("//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Первоначальный взнос')]//input"));
        firstPay.sendKeys(Keys.CONTROL + "A");
        firstPay.sendKeys("3 058 000");
        Assert.assertEquals("В поле ПЕРВОНАЧАЛЬНЫЙ ВЗНОС введена неверная сумма",
                "3 058 000", firstPay.getAttribute("value"));

        WebElement period = driver.findElement(By.xpath("//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Срок кредита')]//input"));
        period.sendKeys(Keys.CONTROL + "A");
        period.sendKeys("30");
        Assert.assertEquals("В поле СРОК КРЕДИТА введена неверная сумма",
                "30", period.getAttribute("value"));

        WebElement buttonIssue = driver.findElement(By.xpath("//button[contains(.,'Получить одобрение')]"));
        scrollToElementJs(buttonIssue);



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
