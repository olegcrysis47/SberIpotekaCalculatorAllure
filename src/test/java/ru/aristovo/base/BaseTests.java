package ru.aristovo.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.aristovo.framework.managers.InitManager;
import ru.aristovo.framework.managers.PageManager;
import ru.aristovo.framework.managers.TestPropManager;

import static ru.aristovo.framework.managers.DriverManager.getDriver;
import static ru.aristovo.framework.utils.PropConst.APP_URL;

public class BaseTests {

    protected PageManager app = PageManager.getPageManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }

}
