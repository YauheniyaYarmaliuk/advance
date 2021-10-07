package cdp.test.automation.pages;

import cdp.test.automation.pages.components.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage extends BasePage {
    private final Logger logger = LogManager.getLogger(DashboardPage.class);
    public SelenideElement pageTitle = $(".breadcrumbs span");
    SelenideElement addDashboardBtn = $(".dashboard-header .rp-icons-add-dash");
    ElementsCollection dashboardList = $$(".dashboard-table-row");
    SelenideElement tableViewBtn = $(".dashboards-view-table");
    SelenideElement demoDashBoard = $(".grid-stack-animate");
    String editBtn = ".dashboard-edit-col i";
    String deleteBtn = ".rp-icons-delete";
    String nameLink = ".name";

    public DashboardPage() {
        super();
    }

    public DashboardPage visiblePage() {
        pageTitle.shouldBe(visible);
        return this;
    }

    public AddNewDashboardPopup clickAddDashBtn() {
        logger.info("Click dashboard button");
        addDashboardBtn.click();
        return new AddNewDashboardPopup();
    }

    public DashboardPage clickTableViewBtn() {
        tableViewBtn.shouldBe(visible);
        tableViewBtn.click();
        return this;
    }

    public LaunchGadget demoDashboardVisible() {
        demoDashBoard.waitUntil(visible, 3000);
        return new LaunchGadget();
    }

    public DashboardPage clickNameLink(ElementsCollection newList) {
        newList.get(0).findAll(nameLink).get(0).click();
        return this;
    }

    public SettingsPage clickSettingBtn() {
        new HeaderMenu().clickSettingButton();
        return new SettingsPage();
    }

    public AddNewDashboardPopup clickEditBtn(ElementsCollection newList) {
        newList.get(0).findAll(editBtn).get(0).click();
        return new AddNewDashboardPopup();
    }

    public DeletePopup clickDeleteBtn(ElementsCollection newList) {
        newList.get(0).findAll(deleteBtn).get(0).click();
        return new DeletePopup();
    }

    public ElementsCollection getSearchListByText(String text) {
        return dashboardList.filterBy(text(text));
    }
}
