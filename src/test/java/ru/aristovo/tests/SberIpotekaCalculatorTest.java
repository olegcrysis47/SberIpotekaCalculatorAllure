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
        waitThread(1000);
        Assert.assertEquals("В поле СТОИМОСТЬ НЕДВИЖИМОСТИ введена неверная сумма",
                "5 180 000", price.getAttribute("value"));

        WebElement firstPay = driver.findElement(By.xpath("//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Первоначальный взнос')]//input"));
        firstPay.sendKeys(Keys.CONTROL + "A");
        firstPay.sendKeys("3 058 000");
        waitThread(1000);
        Assert.assertEquals("В поле ПЕРВОНАЧАЛЬНЫЙ ВЗНОС введена неверная сумма",
                "3 058 000", firstPay.getAttribute("value"));

        WebElement period = driver.findElement(By.xpath("//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Срок кредита')]//input"));
        period.sendKeys(Keys.CONTROL + "A");
        period.sendKeys("30");
        waitThread(1000);
        Assert.assertEquals("В поле СРОК КРЕДИТА введена неверная сумма",
                "30", period.getAttribute("value"));

        WebElement buttonIssue = driver.findElement(By.xpath("//button[contains(.,'Получить одобрение')]"));
        scrollToElementJs(buttonIssue);

        WebElement domKlick = driver.findElement(By.xpath("//div[@data-e2e-id='mland-calculator/discount-item-1-switcher']//input"));
        domKlick.click();
        waitThread(1000);
        Assert.assertEquals("ДОМКЛИК - Переключение не сработало", "false", domKlick.getAttribute("aria-checked"));

        WebElement insurance = driver.findElement(By.xpath("//div[@data-e2e-id='mland-calculator/discount-item-2-switcher']//input"));
        insurance.click();
        waitThread(1000);
        Assert.assertEquals("СТРАХОВАНИЕ ЖИЗНИ - Переключение не сработало", "false", insurance.getAttribute("aria-checked"));

        WebElement youngFamily = driver.findElement(By.xpath("//div[@data-e2e-id='mland-calculator/discount-item-6-switcher']//input"));
        Assert.assertEquals("МОЛОДАЯ СЕМЬЯ - переключатель выключен", "true", youngFamily.getAttribute("aria-checked"));

        WebElement electRegist = driver.findElement(By.xpath("//div[@data-e2e-id='mland-calculator/discount-item-7-switcher']//input"));
        electRegist.click();
        waitThread(1000);
        Assert.assertEquals("ЭЛЕКТРОННАЯ РЕГИСТРАЦИЯ - Переключение не сработало", "false", electRegist.getAttribute("aria-checked"));


        WebElement monthPayment = driver.findElement(By.xpath("//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']//span"));
        Assert.assertEquals("Месячный платеж не верен", "16 922 ₽", monthPayment.getText());

        WebElement creditSum = driver.findElement(By.xpath("//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']//span"));
        Assert.assertEquals("Сумма не верна", "2 122 000 ₽", creditSum.getText());

        WebElement reqIncome = driver.findElement(By.xpath("//span[@data-e2e-id='mland-calculator/medium-result-required-income']//span"));
        Assert.assertEquals("З/п не верен", "21 784 ₽", reqIncome.getText());

        WebElement creditRate = driver.findElement(By.xpath("//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']//span"));
        Assert.assertEquals("Ставка не верна", "8,9%", creditRate.getText());

        waitThread(2000);


    }

    public void waitThread(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollMy(int x, int y) {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(" + x + ","
                + y + ");");
    }

    public void scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driver).executeScript(code, element, x, y);
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
