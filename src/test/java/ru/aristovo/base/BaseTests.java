package ru.aristovo.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 1000);

        // 1. открыть dns-shop
        String baseUrl = "https://www.sberbank.ru/ru/person/";
        driver.get(baseUrl);
    }

    @After
    public void after() {
        driver.quit();
    }

}
