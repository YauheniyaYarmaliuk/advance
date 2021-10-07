package cdp.test.automation.runners;

import cdp.test.automation.api.SlackServiceApi;
import cdp.test.automation.core.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static cdp.test.automation.data.Urls.BASE_RP_URL;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class JUnitRunner {
    SlackServiceApi slackApi = new SlackServiceApi();

    @BeforeAll
    public static void setupClass() {
        DriverFactory.create();
    }

    @BeforeEach
    public void OpenWebPage(TestInfo testInfo) {
        String message = "Test is running " + testInfo.getDisplayName();
        slackApi.postCommentIntoSlack(message);
        open(BASE_RP_URL);
    }

    @AfterEach
    public void closeBrowser() {
        close();
        String message = "Test Finished";
        slackApi.postCommentIntoSlack(message);
    }
}
