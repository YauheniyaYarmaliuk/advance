package cdp.test.automation.data;

public class Urls {
    public static final String BASE_RP_URL = "http://localhost:8080";
    public static final String BASE_PR_PATH = "/api/v1";
    public static final String OAUTH_TOKEN_URL = "/uat/sso/oauth/token";
    public static final String USER_URL = "/user";
    public static final String PROJECT_URL = "/project";
    public static final String CHANGE_PASS_URL = USER_URL + "/password/change";
    public static final String LOGIN_URL = USER_URL + "/{login}";
    public static final String PROJECT_NAME_URL = PROJECT_URL + "/{projectName}";
    public static final String DASHBOARD_NAME_URL = "/{projectName}/dashboard";
    public static final String DASHBOARD_ID_URL = "/{projectName}/dashboard/{dashboardId}";
    public static final String FILTER_URL = "/{projectName}/filter";
    public static final String WIDGET_URL = "/{projectName}/widget";
    public static final String FILTER_ID_URL = "/{projectName}/filter/{filterId}";
    public static final String WIDGET_ID_URL = "/{projectName}/widget/{widgetId}";
    public static final String RP_PAGE_URL = "/ui";
    public static final String SAUCE_LAB_OAUTH_URL = "";
    public static final String BASE_JIRA_URL = "http://localhost:9090";
    public static final String BASE_PATH_JIRA_URL = "/rest/api/2/issue";
    public static final String STATUS_URL = "/{issueId}?fields=status";
    public static final String COMMENT_URL = "/{issueId}/comment";
    public static final String TRANSITIONS_URL = "/{issueId}/transitions";
    public static final String GOOGLE_URL = "https://www.google.com";
    public static final String SLACK_BASE_URL = "https://hooks.slack.com/services";
    public static final String SLACK_AUTH_URL = "/TQZHENR28/BQZHH9Y3E/yHqKEu1m8V09nUsuVFVuopi6";
}
