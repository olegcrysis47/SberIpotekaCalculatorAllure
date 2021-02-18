package ru.aristovo.framework.managers;

import java.util.concurrent.TimeUnit;

import static ru.aristovo.framework.managers.DriverManager.getDriver;
import static ru.aristovo.framework.managers.DriverManager.quitDriver;
import static ru.aristovo.framework.utils.PropConst.IMPLICITLY_WAIT;
import static ru.aristovo.framework.utils.PropConst.PAGE_LOAD_TIMEOUT;

public class InitManager {

    private static final TestPropManager props = TestPropManager.getTestPropManager();

    public static void initFramework() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
    }
}
