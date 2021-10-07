package cdp.test.automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features={"src/test/resources/features/reportPortalTests.feature"},
        plugin = {"pretty"},
        monochrome = true,
        glue = {"cdp.test.automation.steps"}
)
public class RunCucumberTest {
}
