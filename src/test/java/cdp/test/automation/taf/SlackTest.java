package cdp.test.automation.taf;

import cdp.test.automation.api.SlackServiceApi;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SlackTest {
    SlackServiceApi api = new SlackServiceApi();

    @DisplayName("Sent message into slack")
    @Test
    public void sentMessageIntoSlack() {
        String message = "You are rock!";
        int status = api.postCommentIntoSlack(message);
        Assertions.assertEquals(status, HttpStatus.SC_OK, "status should be successfully");
    }
}
