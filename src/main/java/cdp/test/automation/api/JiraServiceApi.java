package cdp.test.automation.api;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static cdp.test.automation.api.RestAssuredHelper.getHeader;
import static cdp.test.automation.data.LoginData.JIRA_PASSWORD;
import static cdp.test.automation.data.LoginData.JIRA_USERNAME;
import static cdp.test.automation.data.Urls.*;
import static io.restassured.RestAssured.given;

public class JiraServiceApi {
    private final Logger logger = LogManager.getLogger(JiraServiceApi.class);
    RequestSpecification requestSpec;

    private String getAccessToken() {
        return "Basic " + Base64.getEncoder().encodeToString((JIRA_USERNAME + ":" + JIRA_PASSWORD).getBytes());
    }

    public void setRequestSpec() {
        logger.info("set Authorization token");
        RestAssured.baseURI = BASE_JIRA_URL;
        RestAssured.basePath = BASE_PATH_JIRA_URL;
        requestSpec = getHeader()
                .addHeader("Authorization", getAccessToken())
                .build();
    }

    public String createIssue() {
        logger.info("create new issue");
        return given().spec(requestSpec)
                .body(new File("src/test/resources/issue.json"))
                .when().post().then()
                .extract().response().jsonPath().get("id");
    }

    public int getIssueStatus(String issueId) {
        logger.info("get issue status");
        return given().spec(requestSpec).pathParam("issueId", issueId)
                .get(STATUS_URL).getStatusCode();
    }

    public int postComment(String issueId, String comment) {
        logger.info("sent comment");
        Map<String, String> body = new HashMap<>();
        body.put("body", comment);
        return given().spec(requestSpec).pathParam("issueId", issueId)
                .body(body)
                .post(COMMENT_URL)
                .statusCode();
    }

    public int updateStatus(String issueId) {
        logger.info("update status issue");
        String body = "{\"transition\":{\"id\":\"11\"}}";
        return given().spec(requestSpec).pathParam("issueId", issueId)
                .body(body)
                .post(TRANSITIONS_URL)
                .statusCode();
    }
}
