package cdp.test.automation.pages;

import cdp.test.automation.listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoPage {
    private final Logger logger = LogManager.getLogger(DemoPage.class);
    private WebDriver driver;
    private By statisticPanel = By.xpath("//h2[contains(text(), 'OVERALL')]");
    private By investLaunch = By.xpath("//h2[contains(text(), 'INVESTIGATED')]");
    private By statBlock = By.cssSelector(".overall-statistics");
    private By statisticResize = By.xpath("//*[contains(@class,'overall-statistics')]/../../../../div[contains(@class,'ui-resizable-sw')]");
    private By statisticScroll = By.cssSelector(".overall-statistics .left-widget-wrapper .baron__bar-vertical");

    public DemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public DemoPage dragAndDrop() {
        waitElement(statisticPanel);
        WebElement from = driver.findElement(statisticPanel);
        WebElement to = driver.findElement(investLaunch);
        Actions act = new Actions(driver);
        act.dragAndDrop(from, to).build().perform();
        logger.info("Screenshot after dragAndDrop: " + ScreenshotListener.takeScreenshot(driver));
        return this;
    }

    public void scrollStat() {
        moveToElement(driver.findElement(statisticScroll));
        scrollIntoEl(driver.findElement(statisticScroll));
    }

    public void resizeWidget(int x, int y) {
        waitElement(statBlock);
        scrollIntoEl(driver.findElement(statBlock));
        WebElement el = driver.findElement(statisticResize);
        moveToElement(el);
        resize(el, x, y);
    }

    public String getStaticBlockWidth() {
        waitElement(statBlock);
        scrollIntoEl(driver.findElement(statBlock));
        return driver.findElement(statBlock).getCssValue("width");
    }

    public int getStaticBlockYLocation() {
        waitElement(statBlock);
        return driver.findElement(statBlock).getLocation().getY();
    }

    private void scrollIntoEl(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    private void moveToElement(WebElement element) {
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        ((JavascriptExecutor) driver).executeScript(mouseOverScript, element);
    }

    private void waitElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void resize(WebElement elementToResize, int xOffset, int yOffset) {
        try {
            if (elementToResize.isDisplayed()) {
                Actions action = new Actions(driver);
                action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
                logger.info("Screenshot after clickAndHold: " + ScreenshotListener.takeScreenshot(driver));
            } else {
                logger.error("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("Element with " + elementToResize + "is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            logger.error("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            logger.error("Unable to resize" + elementToResize + " - " + e.getStackTrace());
        }
    }
}
