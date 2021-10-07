package cdp.test.automation.taf;

import cdp.test.automation.api.JiraServiceApi;
import cdp.test.automation.api.SlackServiceApi;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

public class JiraTest {
    JiraServiceApi api = new JiraServiceApi();
    SlackServiceApi slackApi = new SlackServiceApi();

    @BeforeEach
    public void init(TestInfo testInfo) {
        api.setRequestSpec();
        String message = "Test is running " + testInfo.getDisplayName();
        slackApi.postCommentIntoSlack(message);
    }

    @AfterEach
    public void after() {
        String message = "Test Finished";
        slackApi.postCommentIntoSlack(message);
    }

    @DisplayName("Add comment into issue")
    @Test
    public void addCommentIntoJira() {
        String comment = "Awesome!";
        String id = api.createIssue();
        Assertions.assertNotNull(id, "issue should be created successfully");
        int status = api.getIssueStatus(id);
        Assertions.assertEquals(status, HttpStatus.SC_OK, "status should be successfully");
        int statusPostMsg = api.postComment(id, comment);
        Assertions.assertEquals(statusPostMsg, HttpStatus.SC_CREATED, "comment should be sent successfully");
    }

    @DisplayName("Update status od issue")
    @Test
    public void updateStatus() {
        String id = api.createIssue();
        Assertions.assertNotNull(id, "issue should be created successfully");
        int updateStatus = api.updateStatus(id);
        Assertions.assertEquals(updateStatus, HttpStatus.SC_NO_CONTENT, "status should be updated successfully");
    }
}
