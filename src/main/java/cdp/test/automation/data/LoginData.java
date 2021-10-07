package cdp.test.automation.data;

import cdp.test.automation.model.User;

public class LoginData {
    public static final String ADMIN_USERNAME = "superadmin";
    public static final String ADMIN_PASSWORD = "erebus";
    public static final String USER_USERNAME = "default";
    public static final String USER_PASSWORD = "1q2w3e";
    public static final String NEW_USERNAME = "Jake";
    public static final String NEW_PASSWORD = "user";
    public static final String JIRA_USERNAME = "Yauheniya_Yarmaliuk";
    public static final String JIRA_PASSWORD = "bart952134";
    public static final String TOKEN = "Basic dWk6dWltYW4=";

    public static User getUser() {
        return new User(USER_USERNAME, USER_PASSWORD);
    }
    public static User getAdmin() {
        return new User(ADMIN_USERNAME, ADMIN_PASSWORD);
    }
}
