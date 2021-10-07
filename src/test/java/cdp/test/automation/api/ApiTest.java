package cdp.test.automation.api;

import cdp.test.automation.core.DriverFactory;
import cdp.test.automation.pages.DemoPage;
import cdp.test.automation.steps.Steps;
import com.codeborne.selenide.Selenide;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.IOException;

import static cdp.test.automation.data.LoginData.*;
import static cdp.test.automation.data.Urls.*;
import static cdp.test.automation.utils.JsScripts.setItemInLocalStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ApiTest {
    WebServiceApi api = new WebServiceApi();
    Steps steps = new Steps();
    String token;

    @BeforeEach
    public void setAdminToken() {
        token = setAndGetToken(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    @DisplayName("CRUD User")
    @Test
    public void createUpdateDeleteUser() throws IOException {
        String userName = "Jake";
        String projectName = "newProject";
        int createPrStatus = api.createProject(projectName);
        Assert.assertEquals(createPrStatus, HttpStatus.SC_CREATED, "project should be successfully created");
        int createUsStatus = api.createUser(userName, projectName);
        Assert.assertEquals(createUsStatus, HttpStatus.SC_CREATED, "user should be successfully created");
        setAndGetToken(NEW_USERNAME, NEW_PASSWORD);
        int updatePassStatus = api.updatePassword();
        Assert.assertEquals(updatePassStatus, HttpStatus.SC_OK, "password should be successfully updated");
        setAndGetToken(ADMIN_USERNAME, ADMIN_PASSWORD);
        int deleteUsStatus = api.deleteUser(userName);
        Assert.assertEquals(deleteUsStatus, HttpStatus.SC_OK, "user should be successfully deleted");
        int deletePrStatus = api.deleteProject(projectName);
        Assert.assertEquals(deletePrStatus, HttpStatus.SC_OK, "project should be successfully deleted");
    }

    @DisplayName("CRUD Dashboard")
    @Test
    public void createUpdateDeleteDashboard() {
        String projectName = "dashboardProject";
        int createPrStatus = api.createProject(projectName);
        Assert.assertEquals(createPrStatus, HttpStatus.SC_CREATED, "project should be successfully created");
        String dashboardId = api.createDashboard(projectName);
        Assert.assertNotNull(dashboardId, "dashboard should be successfully created");
        int updateDashStatus = api.updateDashSize(projectName, dashboardId, "200");
        Assert.assertEquals(updateDashStatus, HttpStatus.SC_OK, "dashboard should be successfully updated");
        int deleteDashStatus = api.deleteDashboard(projectName, dashboardId);
        Assert.assertEquals(deleteDashStatus, HttpStatus.SC_OK, "dashboard should be successfully deleted");
        int deletePrStatus = api.deleteProject(projectName);
        Assert.assertEquals(deletePrStatus, HttpStatus.SC_OK, "project should be successfully deleted");
    }

    @DisplayName("CRUD Widget")
    @Test
    public void createUpdateDeleteWidget() {
        String projectName = "dashboardProject";
        int createPrStatus = api.createProject(projectName);
        Assert.assertEquals(createPrStatus, HttpStatus.SC_CREATED, "project should be successfully created");
        String dashboardId = api.createDashboard(projectName);
        Assert.assertNotNull(dashboardId, "dashboard should be successfully created");
        String filterId = api.createFilter(projectName);
        Assert.assertNotNull(filterId, "filter should be successfully created");
        String widgetId = api.createWidget(projectName, filterId);
        Assert.assertNotNull(widgetId, "widjet should be successfully created");
        int addWidStatus = api.addWidget(projectName, dashboardId, widgetId);
        Assert.assertEquals(addWidStatus, HttpStatus.SC_OK, "widjet should be add to dashboard");
        int deleteWidStatus = api.deleteWidget(projectName, widgetId);
        Assert.assertEquals(deleteWidStatus, HttpStatus.SC_METHOD_NOT_ALLOWED, "widget should not be deleted");
        int deleteFilter = api.deleteFilter(projectName, filterId);
        Assert.assertEquals(deleteFilter, HttpStatus.SC_OK, "filter should be successfully deleted");
        int deletePrStatus = api.deleteProject(projectName);
        Assert.assertEquals(deletePrStatus, HttpStatus.SC_OK, "project should be successfully deleted");
    }

    @DisplayName("Mix test with driver and api to update Widget size")
    @Test
    public void modifyWidget() {
        DriverFactory.create();
        open(BASE_RP_URL);
        setItemInLocalStorage("session_token", "bearer " + token);
        open(BASE_RP_URL + RP_PAGE_URL);
        DemoPage demoPage = new DemoPage(getWebDriver());
        String projectName = "superadmin_personal";
        String postfix = "new";
        String dashboardId = api.createDashboard(projectName);
        Assert.assertNotNull(dashboardId, "dashboard should be successfully created");
        String filterId = api.createFilter(projectName);
        Assert.assertNotNull(filterId, "filter should be successfully created");
        String widgetId = api.createWidget(projectName, filterId);
        Assert.assertNotNull(widgetId, "widjet should be successfully created");
        int addWidStatus = api.addWidget(projectName, dashboardId, widgetId);
        Assert.assertEquals(addWidStatus, HttpStatus.SC_OK, "widjet should be add to dashboard");
        steps.clickDashboardMenu();
        Selenide.refresh();
        steps.clickExistDemoByPostfix(postfix);
        String initialWidth = demoPage.getStaticBlockWidth();
        demoPage.resizeWidget(100, 150);
        String updateWidth = demoPage.getStaticBlockWidth();
        Assertions.assertNotEquals(initialWidth, updateWidth, "Width should be updated");
        int deleteDashStatus = api.deleteDashboard(projectName, dashboardId);
        Assert.assertEquals(deleteDashStatus, HttpStatus.SC_OK, "dashboard should be successfully deleted");
        int deleteFilter = api.deleteFilter(projectName, filterId);
        Assert.assertEquals(deleteFilter, HttpStatus.SC_OK, "filter should be successfully deleted");
    }

    private String setAndGetToken(String login, String password) {
        String token = api.getAccessAuthToken(login, password);
        api.setRequestSpec(token);
        return token;
    }
}
