package cdp.test.automation.ui;

import cdp.test.automation.model.User;
import cdp.test.automation.pages.DashboardPage;
import cdp.test.automation.pages.components.EditWidget;
import cdp.test.automation.pages.components.LaunchGadget;
import cdp.test.automation.runners.TestNgRunner;
import cdp.test.automation.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.Test;

import static cdp.test.automation.data.LoginData.getUser;

public class UITestsNG extends TestNgRunner {
    Steps steps = new Steps();
    User user = getUser();

    @Test(testName = "Verify user is able to edit all the widgets on demo dashboard")
    public void editAllWidgetInTheDashboard() {
        String expWidgetUpdMsg = "Widget has been updated!";
        String postfix = steps.createDashBoard(user);
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        int launchSize = new LaunchGadget().getLaunchesSize();
        for (int i = 0; i < launchSize; i++) {
            new DashboardPage().demoDashboardVisible().editIcon.get(i).scrollIntoView(true).hover().click();
            String actWidgetUpdMsg = new EditWidget().editSettingsWidget()
                    .clickSubmitButton().getMessageTest();
            Assert.assertEquals(expWidgetUpdMsg, actWidgetUpdMsg, "Each widget should updated by User");
        }
    }

    @Test(testName = "Verify Tooltips behavior")
    public void verifyTooltipsBehavior() {
        String postfix = steps.create,DashBoard(user);
        String expDefectType = "To Investigate";
        steps.clickDashboardMenu();
        steps.clickExistDemoByPostfix(postfix);
        String actDefectType = steps.getFirstDefectType();
        Assert.assertEquals(actDefectType, expDefectType, "Defect type should be " + expDefectType);
    }
}
