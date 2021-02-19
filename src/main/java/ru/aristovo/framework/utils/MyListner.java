package ru.aristovo.framework.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.aristovo.framework.managers.DriverManager;

public class MyListner extends AllureJunit4 {

    @Override
    public void testFailure(Failure failure) {
        addScreenshot();
        super.testFailure(failure);
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
