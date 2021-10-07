package cdp.test.automation.taf;

import cdp.test.automation.runners.SourceLabRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static cdp.test.automation.data.Urls.GOOGLE_URL;

public class SourceLabTests extends SourceLabRunner {
    private static final Logger logger = LogManager.getLogger(SourceLabTests.class);

    @DisplayName("Search in google")
    @Test
    public void searchInGoogle() {
        String searchWord = "Netflix";
        driver.navigate().to(GOOGLE_URL);
        logger.debug("google should be open");
        WebElement inputField = driver.findElement(By.cssSelector("[name='q']"));
        inputField.sendKeys(searchWord);
        inputField.sendKeys(Keys.ENTER);
        logger.debug("Netflix was put to the Search input box");
        String title = driver.getTitle();
        Assertions.assertTrue(title.contains(searchWord), "title should be contain " + searchWord);
    }
}
