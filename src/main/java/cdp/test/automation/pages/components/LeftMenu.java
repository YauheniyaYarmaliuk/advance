package cdp.test.automation.pages.components;

import cdp.test.automation.pages.DashboardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LeftMenu {
    SelenideElement dashboardMenuBtn = $("#dashboard");

    public DashboardPage clickDashboardMenuBtn() {
        dashboardMenuBtn.shouldBe(Condition.visible).click();
        return new DashboardPage();
    }
}
