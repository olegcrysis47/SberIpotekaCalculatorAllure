package ru.aristovo.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static ru.aristovo.framework.managers.DriverManager.getDriver;

public class IpotekaCalcPage extends BasePage {

    @FindBy(xpath = "//div[@class='kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']//h1")
    WebElement titlePageIpoteka;
    @FindBy(xpath = "//h2[contains(.,'Рассчитайте ипотеку')]")
    WebElement titleCalculateIpoteka;
    @FindBy(xpath = "//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Стоимость недвижимости')]//input")
    WebElement price;
    @FindBy(xpath = "//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Первоначальный взнос')]//input")
    WebElement firstPay;
    @FindBy(xpath = "//span[@class='_3akqIukcHrgIDOuebcl58f']//div[contains(.,'Срок кредита')]//input")
    WebElement period;
    @FindBy(xpath = "//button[contains(.,'Получить одобрение')]")
    WebElement buttonIssue;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-1-switcher']//input")
    WebElement domKlick;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-2-switcher']//input")
    WebElement insurance;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-6-switcher']//input")
    WebElement youngFamily;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-7-switcher']//input")
    WebElement electRegist;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-monthly-payment']//span")
    WebElement monthPayment;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']//span")
    WebElement creditSum;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-required-income']//span")
    WebElement reqIncome;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']//span")
    WebElement creditRate;

    @Step("Проверяем открывшуюся страницу")
    public IpotekaCalcPage checkedTitlePage() {
        Assert.assertEquals("Заголовок страницы не соответствует ожидаемому",
                "Ипотека от 7,3%* на готовые квартиры", titlePageIpoteka.getText());
        return this;
    }

    @Step("Прокручиваем страницу до калькулятора ипотеки")
    public IpotekaCalcPage scrollToCalculatorAnsSelectFrame() {
        scrollToElementJs(titleCalculateIpoteka);
        getDriver().switchTo().frame("iFrameResizer0");
        return this;
    }

    @Step("Заполняем поле калькулятора '{nameField}' данными '{value}'")
    public IpotekaCalcPage fillFieldInCalculator(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillField(price, value);
                element = price;
                break;
            case "Первоначальный взнос":
                fillField(firstPay, value);
                element = firstPay;
                break;
            case "Срок кредита":
                fillField(period, value);
                element = period;
                break;
            default:
                Assert.fail("Поле " + nameField + " отсутствует для заполнения");
        }
        Assert.assertEquals("В поле '" +  nameField + "' введены неверные данные",
                value, element.getAttribute("value"));
        return this;
    }

    @Step("Прокручиваем калькулятор до дополнительных услуг")
    public IpotekaCalcPage scrollByButtonIssue() {
        scrollToElementJs(buttonIssue);
        return this;
    }

    @Step("Переключаем дополнительные услуги '{servName}' на '{value}'")
    public IpotekaCalcPage selectAddServ(String servName, String value) {
        WebElement element = null;
        switch (servName) {
            case "Скидка 0,3% при покупке квартиры на ДомКлик":
                selectServ(domKlick, value);
                element = domKlick;
                break;
            case "Страхование жизни и здоровья":
                selectServ(insurance, value);
                element = insurance;
                break;
            case "Молодая семья":
                selectServ(youngFamily, value);
                element = youngFamily;
                break;
            case "Электронная регистрация сделки":
                selectServ(electRegist, value);
                element = electRegist;
                break;
            default:
                Assert.fail("Переключатель " + servName + " на странице отсутствует.");
        }
        Assert.assertEquals(servName + " - переключение не сработало", value, element.getAttribute("aria-checked"));
        return this;
    }

    @Step("Проверяем расчетное поле калькулятора '{nameMenu}', ожидаем значение '{value}'")
    public IpotekaCalcPage checkedFieldCalc(String fieldName, String value) {
        WebElement element = null;
        switch (fieldName) {
            case "Ежемесячный платеж":
                element = monthPayment;
                break;

            case "Сумма кредита":
                element = creditSum;
                break;

            case "Необходимый доход":
                element = reqIncome;
                break;

            case "Процентная ставка":
                element = creditRate;
                break;
            default:
                Assert.fail("Поле " + fieldName + " не найдено!");
        }
        Assert.assertEquals("Поле " + fieldName + " рассчитано не верно", value, element.getText());
        return this;
    }







}
