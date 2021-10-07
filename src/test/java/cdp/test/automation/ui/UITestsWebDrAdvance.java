package cdp.test.automation.ui;

import cdp.test.automation.model.User;
import cdp.test.automation.pages.DemoPage;
import cdp.test.automation.runners.JUnitRunner;
import cdp.test.automation.steps.Steps;
import org.junit.jupiter.api.*;

import static cdp.test.automation.data.LoginData.getUser;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UITestsWebDrAdvance extends JUnitRunner {
    Steps steps = new Steps();
    User user = getUser();

    @DisplayName("Move Dashboard")
    @Test
    public void resizeDashboardWidget() {
        DemoPage demoPage = new DemoPage(getWebDriver());
        String postfix = steps.createDashBoard(user);
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        int initLocation = demoPage.getStaticBlockYLocation();
        demoPage.dragAndDrop();
        int pastMove = demoPage.getStaticBlockYLocation();
        Assertions.assertNotEquals(initLocation, pastMove, "Location should be different");
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        int stateMove = demoPage.getStaticBlockYLocation();
        Assertions.assertEquals(pastMove, stateMove, "Location should be different");
    }

    @DisplayName("Resize Dashboard")
    @Test
    public void widthDashboardWidget() {
        DemoPage demoPage = new DemoPage(getWebDriver());
        String postfix = steps.createDashBoard(user);
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        String initialWidth = demoPage.getStaticBlockWidth();
        demoPage.resizeWidget(100, 150);
        String updateWidth = demoPage.getStaticBlockWidth();
        Assertions.assertNotEquals(initialWidth, updateWidth, "Width should be updated");
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        String afterWidth = demoPage.getStaticBlockWidth();
        Assertions.assertEquals(updateWidth, afterWidth, "Width should be the same as previous");
        demoPage.resizeWidget(5000, 5000);
        String maxWidth = demoPage.getStaticBlockWidth();
        Assertions.assertEquals(afterWidth, maxWidth, "Width should be the same as previous");
    }

    @DisplayName("Scroll Dashboard")
    @Test
    public void scrollStatisticWidget() {
        DemoPage demoPage = new DemoPage(getWebDriver());
        String postfix = steps.createDashBoard(user);
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        demoPage.resizeWidget(50, 50);
        demoPage.scrollStat();
    }
}
