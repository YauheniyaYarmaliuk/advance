package cdp.test.automation.api;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static cdp.test.automation.api.RestAssuredHelper.getHeader;
import static cdp.test.automation.data.Urls.SLACK_AUTH_URL;
import static cdp.test.automation.data.Urls.SLACK_BASE_URL;
import static io.restassured.RestAssured.given;

public class SlackServiceApi {
    private final Logger logger = LogManager.getLogger(SlackServiceApi.class);

    public RequestSpecification setRequestSpec() {
        RestAssured.baseURI = SLACK_BASE_URL;
        RestAssured.basePath = SLACK_AUTH_URL;
        return getHeader().build();
    }

    public int postCommentIntoSlack(String message) {
        logger.info("post message into slack");
        Map<String, String> body = new HashMap<>();
        body.put("text", message);
        return given().spec(setRequestSpec()).body(body).when().post().getStatusCode();
    }
}
