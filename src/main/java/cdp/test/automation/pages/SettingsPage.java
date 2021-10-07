package cdp.test.automation.pages;

import cdp.test.automation.pages.components.MessageFloatLine;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cdp.test.automation.data.Data.KEEP_LOGS_OPTIONS;
import static cdp.test.automation.utils.RandomDataGen.getRandomFromArrayList;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPage extends BasePage {
    private final Logger logger = LogManager.getLogger(SettingsPage.class);
    SelenideElement keepLogDrDown = $("[data-js-selector='keepLogs']");
    SelenideElement submitBtn = $(".rp-btn-submit");
    SelenideElement demoDataNavigate = $("[data-js-tab-action='demoData'");
    SelenideElement postfixInput = $("[name='postfix_name']");
    SelenideElement loader = $(".demo-data-loader[style='display: none;']");

    public SettingsPage() {
        super();
    }

    public SettingsPage fillKeepLogs() {
        logger.info("Setting page open");
        keepLogDrDown.shouldBe(visible).click();
        $(byText(getRandomFromArrayList(KEEP_LOGS_OPTIONS))).click();
        return this;
    }

    public MessageFloatLine loaderHide() {
        loader.waitUntil(exist, 50000);
        return new MessageFloatLine();
    }

    public MessageFloatLine clickSubmitButton() {
        logger.info("Clicking submit button on Setting");
        submitBtn.click();
        return new MessageFloatLine();
    }

    public SettingsPage clickDemoDataNavigate() {
        logger.info("Click demo data section");
        demoDataNavigate.shouldBe(visible).click();
        return this;
    }

    public SettingsPage fillPostfixField(String postfix) {
        logger.info("Postfix input field");
        postfixInput.shouldBe(visible).setValue(postfix);
        return this;
    }
}
