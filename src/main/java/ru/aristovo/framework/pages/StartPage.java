package ru.aristovo.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//a[@class=' kitt-top-menu__link kitt-top-menu__link_first kitt-top-menu__link_icons kitt-top-menu__link_wide']")
    List<WebElement> menuSber;

    @FindBy(xpath = "//a[@class='kitt-top-menu__link kitt-top-menu__link_second']")
    List<WebElement> subMenuSber;

    public StartPage selectMenuSber(String nameMenu) {
        for (WebElement w:menuSber) {
            if (w.getAttribute("aria-label").equalsIgnoreCase(nameMenu)) {
                elementToBeClickable(w);
                w.click();
                return this;
            }
        }
        Assert.fail("Меню " + nameMenu + " не было найдено");
        return this;
    }

    public IpotekaCalcPage selectSubMenuSber(String nameSubMenu) {
        for (WebElement w:subMenuSber) {
            if (w.getText().equalsIgnoreCase(nameSubMenu)) {
                elementToBeClickable(w);
                w.click();
                return app.getIpotekaCalcPage();
            }
        }
        Assert.fail("Подменю " + nameSubMenu + " не было найдено");
        return app.getIpotekaCalcPage().checkedTitlePage();
    }
}



