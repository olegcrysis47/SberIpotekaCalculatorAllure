package ru.aristovo.tests;

import org.junit.Test;
import ru.aristovo.base.BaseTests;

import java.util.List;

public class SberIpotekaCalculatorTest extends BaseTests {

    @Test
    public void calculatorTest() {

        app.getStartPage()
                .selectMenuSber("ипотека")
                .selectSubMenuSber("Ипотека на готовое жильё")
                .fillFieldInCalculator("Стоимость недвижимости", "5 180 000")
                .fillFieldInCalculator("Первоначальный взнос", "3 058 000")
                .fillFieldInCalculator("Срок кредита", "30")
                .selectAddServ("Скидка 0,3% при покупке квартиры на ДомКлик", "false")
                .selectAddServ("Страхование жизни и здоровья", "false")
                .selectAddServ("Молодая семья", "true")
                .selectAddServ("Электронная регистрация сделки", "false")
                .checkedFieldCalc("Ежемесячный платеж","16 922 ₽")
                .checkedFieldCalc("Сумма кредита", "2 122 000 ₽")
                .checkedFieldCalc("Необходимый доход", "21 784 ₽")
                .checkedFieldCalc("Процентная ставка", "11%");
    }
}
