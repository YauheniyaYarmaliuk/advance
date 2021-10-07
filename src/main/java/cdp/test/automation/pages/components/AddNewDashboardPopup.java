package cdp.test.automation.pages.components;

import cdp.test.automation.pages.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.logging.log4j.LogManager.getLogger;

public class AddNewDashboardPopup extends BasePage {
    private static final Logger logger = getLogger(AddNewDashboardPopup.class);
    SelenideElement nameLine = $("[placeholder='Enter dashboard name']");
    SelenideElement descriptionLine = $("textarea[data-js-description]");
    SelenideElement shareSwitcher = $(".slider");
    SelenideElement addBtn = $(".rp-btn-submit");
    SelenideElement cancelBtn = $(".rp-btn-cancel");

    public AddNewDashboardPopup() {
        super();
    }

    public AddNewDashboardPopup fillNameOfDashboard(String name) {
        logger.info("Fill name of dashboard");
        nameLine.clear();
        nameLine.setValue(name);
        return this;
    }

    public AddNewDashboardPopup fillDescriptionOfDashboard(String description) {
        logger.info("Fill description of dashboard");
        descriptionLine.clear();
        descriptionLine.setValue(description);
        return this;
    }

    public AddNewDashboardPopup clickShareSwitcher() {
        logger.info("Click share switcher");
        shareSwitcher.click();
        return this;
    }

    public MessageFloatLine clickAddBtn() {
        logger.info("Click add button");
        addBtn.shouldBe(visible);
        addBtn.click();
        return new MessageFloatLine();
    }

    public AddNewDashboardPopup clickCancelBtn() {
        logger.info("Click Cancel button");
        cancelBtn.click();
        return this;
    }
}
