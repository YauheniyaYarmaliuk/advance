package cdp.test.automation.api;


import cdp.test.automation.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static cdp.test.automation.api.RestAssuredHelper.getHeader;
import static cdp.test.automation.data.LoginData.*;
import static cdp.test.automation.data.Urls.*;
import static io.restassured.RestAssured.given;

public class WebServiceApi {
    private final Logger logger = LogManager.getLogger(WebServiceApi.class);
    RequestSpecification requestSpec;

    public void setRequestSpec(String token) {
        logger.info("set Authorization token");
        RestAssured.baseURI = BASE_RP_URL;
        RestAssured.basePath = BASE_PR_PATH;
        requestSpec = getHeader()
                .addHeader("Authorization", "bearer " + token)
                .build();
    }

    public String getAccessAuthToken(String login, String password) {
        logger.info("set Access token for user");
        RestAssured.baseURI = BASE_RP_URL;
        RestAssured.basePath = OAUTH_TOKEN_URL;
        return given().spec(getHeader()
                .addHeader("Authorization", TOKEN)
                .build())
                .queryParam("grant_type", "password")
                .queryParam("password", password)
                .queryParam("username", login)
                .when().post().then()
                .extract().response().jsonPath().get("access_token");
    }

    private String getValue() {
        return RandomStringUtils.randomAlphanumeric(4);
    }

    public int createUser(String userName, String projectName) throws IOException {
        logger.info("Create new user");
        User user = new User();
        user.setAccountRole("USER");
        user.setDefault_project(projectName);
        user.setEmail(userName + "@example.com");
        user.setFull_name(userName);
        user.setLogin(NEW_USERNAME);
        user.setPassword(NEW_PASSWORD);
        user.setProjectRole("CUSTOMER");
        ObjectMapper mapper = new ObjectMapper();
        String newUser = mapper.writeValueAsString(user);

        return given().spec(requestSpec)
                .body(newUser)
                .when().post(USER_URL).getStatusCode();
    }

    public int createProject(String projectName) {
        logger.info("Create new project");
        Map<String, String> project = new HashMap<>();
        project.put("addInfo", getValue());
        project.put("customer", getValue());
        project.put("entryType", "INTERNAL");
        project.put("projectName", projectName);

        return given().spec(requestSpec)
                .body(project)
                .when().post(PROJECT_URL).getStatusCode();
    }

    public int updatePassword() {
        logger.info("Update password");
        Map<String, String> password = new HashMap<>();
        password.put("newPassword", "newuser");
        password.put("oldPassword", NEW_PASSWORD);

        return given().spec(requestSpec)
                .body(password)
                .when().post(CHANGE_PASS_URL).getStatusCode();
    }

    public int deleteUser(String login) {
        logger.info("Delete user");
        return given().spec(requestSpec)
                .pathParam("login", login)
                .when().delete(LOGIN_URL).getStatusCode();
    }

    public int deleteProject(String projectName) {
        logger.info("Delete project");
        return given().spec(requestSpec)
                .pathParam("projectName", projectName)
                .when().delete(PROJECT_NAME_URL).getStatusCode();
    }

    public String extract()createDashboard(String projectName) {
        logger.info("Create Dashboard");
        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("description", "NewDashboard");
        dashboard.put("name", "dash#new");
        dashboard.put("share", "true");
        return given().spec(requestSpec)
                .body(dashboard)
                .pathParam("projectName", projectName)
                .when().post(DASHBOARD_NAME_URL).then()
                .extract().response().jsonPath().get("id");
    }

    public int updateDashSize(String projectName, String dashboardId, String size) {
        logger.info("Update Dashboard Size");
        Map<String, String> dashboard = new HashMap<>();
        dashboard.put("widgetSize", size);
        dashboard.put("widgetPosition", "100");
        return given().spec(requestSpec)
                .body(dashboard)
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashboardId)
                .when().put(DASHBOARD_ID_URL).getStatusCode();
    }

    public int deleteDashboard(String projectName, String dashboardId) {
        logger.info("Delete Dashboard");
        return given().spec(requestSpec)
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashboardId)
                .when().delete(DASHBOARD_ID_URL).getStatusCode();
    }

    public String createFilter(String projectName) {
        logger.info("Create new filter");
        ResponseBody json = given().spec(requestSpec)
                .body(new File("src/test/resources/filter.json"))
                .pathParam("projectName", projectName)
                .when().post(FILTER_URL).then()
                .extract().response().getBody();
        return json.jsonPath().getString("id").replace("[", "").replace("]", "");
    }

    public String createWidget(String projectName, String id) {
        logger.info("Create new widget");
        String body = "{\"filter_id\":\"" + id + "\",\"name\":\"new Widget\",\"share\":true,\"content_parameters\":{\"type\":\"statistics_panel\",\"gadget\":\"overall_statistics\",\"metadata_fields\":[\"name\",\"number\",\"start_time\"],\"itemsCount\":50,\"content_fields\":[\"statistics$executions$total\",\"statistics$executions$passed\",\"statistics$executions$failed\",\"statistics$executions$skipped\",\"statistics$defects$product_bug$PB001\",\"statistics$defects$automation_bug$AB001\",\"statistics$defects$system_issue$SI001\",\"statistics$defects$no_defect$ND001\",\"statistics$defects$to_investigate$TI001\"],\"widgetOptions\":{\"viewMode\":[\"panel\"],\"filterName\":[\"JakeFilter\"]}},\"description\":\"widget\"}";
        return given().spec(requestSpec)
                .body(body)
                .pathParam("projectName", projectName)
                .when().post(WIDGET_URL).then()
                .extract().response().getBody().jsonPath().get("id");
    }

    public int deleteFilter(String projectName, String filterId) {
        logger.info("Delete filter");
        return given().spec(requestSpec)
                .pathParam("projectName", projectName)
                .pathParam("filterId", filterId)
                .when().delete(FILTER_ID_URL).getStatusCode();
    }

    public int deleteWidget(String projectName, String widgetId) {
        logger.info("Delete widget");
        return given().spec(requestSpec)
                .pathParam("projectName", projectName)
                .pathParam("widgetId", widgetId)
                .when().delete(WIDGET_ID_URL).getStatusCode();
    }

    public int addWidget(String projectName, String dashBoardId, String widgetId) {
        logger.info("Add widget into dashboard");
        String body = "{\n  \"addWidget\": {\n    \"widgetId\": \"" + widgetId + "\",\n    \"widgetPosition\": [\n      50\n    ],\n    \"widgetSize\": [\n      50\n    ]\n  }  \n}";
        return given().spec(requestSpec)
                .body(body)
                .pathParam("projectName", projectName)
                .pathParam("dashboardId", dashBoardId)
                .when().put(DASHBOARD_ID_URL).getStatusCode();
    }
}

