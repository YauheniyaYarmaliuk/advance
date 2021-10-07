package cdp.test.automation.runners;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static cdp.test.automation.data.Urls.SAUCE_LAB_OAUTH_URL;

public class SourceLabRunner {
   public WebDriver driver;

    @BeforeEach
    public void setupDriver() throws MalformedURLException {
        URL url = new URL(SAUCE_LAB_OAUTH_URL);
        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("seleniumVersion", "3.141.59");
        sauceOpts.setCapability("name", "search in google");
        List<String> tags = Arrays.asList("sauceDemo", "test", "module5", "javaTest");
        sauceOpts.setCapability("tags", tags);
        sauceOpts.setCapability("maxDuration", 3600);
        sauceOpts.setCapability("commandTimeout", 600);
        sauceOpts.setCapability("idleTimeout", 1000);
        sauceOpts.setCapability("build", "Jane Tests");
        ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setExperimentalOption("w3c", true);
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("sauce:options", sauceOpts);
        capabilities.setCapability("goog:chromeOptions", chromeOpts);
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("platformVersion", "Windows 10");
        capabilities.setCapability("browserVersion", "76.0");
        driver = new RemoteWebDriver(url, capabilities);
    }
}
