package cdp.test.automation.core;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/surefire-reports";
    }
}
