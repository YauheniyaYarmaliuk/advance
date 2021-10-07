package cdp.test.automation.pages.components;

import cdp.test.automation.pages.SettingsPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;

import static cdp.test.automation.utils.RandomDataGen.clickThreeRndValFromCollection;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EditWidget {
    SelenideElement criteriaDropDown = $(".dropdown-container");
    ElementsCollection criteriaList = $$(".checkbox.rp-checkbox-wrap");

    public SettingsPage editSettingsWidget() {
        if (criteriaDropDown.is(visible)) {
            clickCriteriaDropDown();
            clickThreeRndValFromCollection(criteriaList);
        } else {
            new AddNewDashboardPopup().fillDescriptionOfDashboard(RandomStringUtils.randomAlphanumeric(5));
        }
        return new SettingsPage();
    }

    private EditWidget clickCriteriaDropDown() {
        criteriaDropDown.shouldBe(Condition.visible).click();
        return this;
    }


}
