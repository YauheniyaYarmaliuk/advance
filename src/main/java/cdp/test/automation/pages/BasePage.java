package cdp.test.automation.pages;

import cdp.test.automation.pages.components.HeaderMenu;
import cdp.test.automation.pages.components.LeftMenu;

public abstract class BasePage {
    protected HeaderMenu headerMenu;
    protected LeftMenu leftMenu;

    public BasePage() {
        headerMenu = new HeaderMenu();
        leftMenu = new LeftMenu();
    }
}
