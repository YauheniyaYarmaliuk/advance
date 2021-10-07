package cdp.test.automation.steps;

import cdp.test.automation.core.DriverFactory;
import cdp.test.automation.pages.LoginPage;
import cdp.test.automation.pages.components.HeaderMenu;
import cdp.test.automation.pages.components.MessageFloatLine;
import com.codeborne.selenide.ElementsCollection;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static cdp.test.automation.data.Urls.BASE_RP_URL;
import static com.codeborne.selenide.Selenide.open;

public class CucumberSteps {
    Steps steps = new Steps();

    @Given("^navigate to RP")
    public void goToRp() {
        DriverFactory.create();
        open(BASE_RP_URL);
    }

    @When("^fill login (.*)$")
    public void fillLogin(String value) {
        new LoginPage().fillUserNameInput(value);
    }

    @When("^fill password (.*)$")
    public void fillPassword(String value) {
        new LoginPage().fillPasswordInput(value);
    }

    @When("^click Submit button$")
    public void clickSubmitBtn() {
        new LoginPage().clickLoginButton();
    }

    @When("^verify message$")
    public void verifySuccessMsg(DataTable dataTable) {
        String actualMsg = new MessageFloatLine().getMessageTest();
        String expectedMsg = dataTable.asList(String.class).get(0).toString();
        Assertions.assertEquals(expectedMsg, actualMsg, "User should login successfully");
    }

    @When("^logout$")
    public void logout() {
        new HeaderMenu().logout();
    }

    @When("^fill wrong login$")
    public void fillWrongLogin(DataTable dataTable) {
        List<String> list = dataTable.asList(String.class);
        new LoginPage().fillUserNameInput(RandomStringUtils.randomAlphanumeric(list.size()));
    }

    @When("^create new Dashboard (.*)$")
    public void createNewDashboard(String name) {
        steps.createNewDashboard(name, name);
    }

    @When("^delete Dashboard (.*)$")
    public void deleteDashboard(String name) {
        ElementsCollection updList = steps.getElements(name);
        steps.deleteDashboard(updList);
    }

}
