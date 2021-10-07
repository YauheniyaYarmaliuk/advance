package cdp.test.automation.runners;

import cdp.test.automation.api.SlackServiceApi;
import cdp.test.automation.core.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static cdp.test.automation.data.Urls.BASE_RP_URL;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class TestNgRunner {
    SlackServiceApi slackApi = new SlackServiceApi();

    @BeforeClass
    public void setupClass() {
        DriverFactory.create();
    }

    @BeforeMethod
    public void OpenWebPage(Method method) {
        String message = "Test is running " + method.getName();
        slackApi.postCommentIntoSlack(message);
        open(BASE_RP_URL);
    }

    @AfterMethod
    public void closeBrowser() {
        close();
        String message = "Test Finished";
        slackApi.postCommentIntoSlack(message);
    }
}
