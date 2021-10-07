package cdp.test.automation.ui;

import cdp.test.automation.model.User;
import cdp.test.automation.runners.JUnitRunner;
import cdp.test.automation.steps.Steps;
import com.codeborne.selenide.ElementsCollection;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import static cdp.test.automation.data.LoginData.getAdmin;
import static cdp.test.automation.data.LoginData.getUser;
import static com.codeborne.selenide.Condition.text;

public class UITestsJUnit extends JUnitRunner {
    private static final Logger logger = LogManager.getLogger(UITestsJUnit.class);
    Steps steps = new Steps();
    User user = getUser();
    String dashboardTitle = "ALL DASHBOARDS";

    @DisplayName("Login in ReportPortal by Admin")
    @Test
    public void loginByAdmin() {
        User admin = getAdmin();
        steps.tryToLogin(admin).pageTitle.shouldHave(text(dashboardTitle));
    }

    @DisplayName("Login in ReportPortal by User")
    @Test
    public void loginByUser() {
        steps.tryToLogin(user).pageTitle.shouldHave(text(dashboardTitle));
    }

    @DisplayName("Setting is changed by User")
    @Test
    public void changeSettingByUser() {
        String expectedMsg = "Project settings were successfully updated";
        steps.tryToLogin(user);
        String actualMsg = steps.updateSettings().getMessageTest();
        Assertions.assertEquals(expectedMsg, actualMsg, "User should update setting successfully");
    }

    @DisplayName("CRUD dashboard by User")
    @Test
    public void addNewDashboardByUser() {
        String expAddMsg = "Dashboard has been added";
        String expUpdateMsg = "Dashboard has been updated";
        String expDeleteMsg = "Dashboard has been deleted!";
        String name = RandomStringUtils.randomAlphanumeric(5);
        String description = RandomStringUtils.randomAlphanumeric(5);
        String descriptionUp = description + "_Up";
        steps.tryToLogin(user);
        String actualAddMsg = steps.createNewDashboard(name, dashboardTitle).getMessageTest();
        Assertions.assertEquals(expAddMsg, actualAddMsg, "User should add new Dashboard successfully");
        ElementsCollection newList = steps.getElements(name);
        Assertions.assertEquals(1, newList.size(), "New dashboard should be created");
        String actualUpdateMsg = steps.editDashboard(newList, descriptionUp).getMessageTest();
        Assertions.assertEquals(expUpdateMsg, actualUpdateMsg, "User should update the Dashboard successfully");
        ElementsCollection updList = steps.getElements(description);
        Assertions.assertEquals(1, updList.size(), "Description should be updated");
        String actDelMsg = steps.deleteDashboard(updList).getMessageTest();
        Assertions.assertEquals(expDeleteMsg, actDelMsg, "User should delete the Dashboard successfully");
        ElementsCollection delList = steps.getElements(name);
        Assertions.assertEquals(0, delList.size(), "Dashboard should be deleted");
    }

}
