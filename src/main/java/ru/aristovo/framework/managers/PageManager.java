package ru.aristovo.framework.managers;

import ru.aristovo.framework.pages.IpotekaCalcPage;
import ru.aristovo.framework.pages.StartPage;

public class PageManager {

    private static PageManager pageManager;

    private StartPage startPage;

    private IpotekaCalcPage ipotekaCalcPage;

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public IpotekaCalcPage getIpotekaCalcPage() {
        if (ipotekaCalcPage == null) {
            ipotekaCalcPage = new IpotekaCalcPage();
        }
        return ipotekaCalcPage;
    }
}
