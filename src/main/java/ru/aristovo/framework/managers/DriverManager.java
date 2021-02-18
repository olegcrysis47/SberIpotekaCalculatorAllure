package ru.aristovo.framework.managers;

import org.apache.commons.exec.OS;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.aristovo.framework.utils.PropConst.*;

public class DriverManager {

    private static WebDriver driver;

    private static DriverManager INSTANCE = null;

    private final TestPropManager props = TestPropManager.getTestPropManager();

    private DriverManager() {
        initDriver();
    }

    public static WebDriver getDriver() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }

    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }

    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }
}
