package cdp.test.automation.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class LaunchGadget {
    SelenideElement investTooltip = $("[data-id='investigated']");
    ElementsCollection trendChart = $$(".c3-bars-to-investigate path");
    ElementsCollection defectTypeCollection = $$(".defect-type-badge span");
    ElementsCollection launchList = $$(".gadget-wrapper");
    public ElementsCollection editIcon = $$("[data-js-gadget-edit]");

    public LaunchGadget goToTooltip() {
        investTooltip.waitUntil(Condition.visible, 3000).click();
        trendChart.get(0).scrollIntoView(true);
        trendChart.findBy(Condition.appear).doubleClick();
        return this;
    }

    public int getLaunchesSize() {
        return launchList.size();
    }

    public String getFirstTextDefectType() {
        defectTypeCollection.get(0).waitUntil(Condition.visible, 2000);
        return defectTypeCollection.iterator().next().scrollIntoView(true).getText();
    }
}
