package cdp.test.automation.core;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;

import static cdp.test.automation.core.DriverManager.setDriver;
import static cdp.test.automation.data.Data.BROWSERS;
import static cdp.test.automation.utils.RandomDataGen.getRandomFromArrayList;
import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public class DriverFactory {

    public static WebDriver create() {
        WebDriver driver = null;
        String browser = getRandomFromArrayList(BROWSERS);
        switch (browser) {
            case "firefox":
                Configuration.browser = FIREFOX;
                break;
            case "chrome":
                Configuration.browser = CHROME;
                break;
            default:
                System.out.println("Unknown browser!");
        }
        setDriver();
        return driver;
    }
}
