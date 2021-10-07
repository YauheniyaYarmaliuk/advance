package cdp.test.automation.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderMenu {
    SelenideElement settingBtn = $(".settings > .mem-set > .material-icons");
    SelenideElement userSettings = $(".arrow_drop_down");
    ElementsCollection logoutBtn = $$("[data-js-logout]");

    public void clickSettingButton() {
        settingBtn.click();
    }

    public void logout() {
        userSettings.click();
        logoutBtn.get(0).click();
    }
}
