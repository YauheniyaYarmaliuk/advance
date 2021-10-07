package cdp.test.automation.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener {
    private static int counter = 0;

    public static String takeScreenshot(WebDriver webDriver) {
        try {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
            String destDir = "./target/test-output/html/screenshots/";
            new File(destDir).mkdirs();
            String destFileName = dateFormat.format(new Date()) + "_" + counter + ".png";
            counter++;
            File destFile = new File(destDir + "/" + destFileName);
            try {
                FileUtils.copyFile(scrFile, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return destFileName;
        } catch (Exception e) {
            return "screenshot not available";
        }
    }
}
