package cdp.test.automation.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    SelenideElement usernameInput = $("input[placeholder='Login']");
    SelenideElement passwordInput = $("input[placeholder='Password']");
    SelenideElement loginButton = $(".rp-btn-login");

    public LoginPage fillUserNameInput(String userName) {
        logger.info("Entering user name on Login page");
        usernameInput.val(userName);
        return this;
    }

    public LoginPage fillPasswordInput(String password) {
        logger.info("Entering password on Login page");
        passwordInput.val(password);
        return this;
    }

    public DashboardPage clickLoginButton() {
        logger.info("Clicking login button on Login page");
        loginButton.click();
        return new DashboardPage();
    }
}
