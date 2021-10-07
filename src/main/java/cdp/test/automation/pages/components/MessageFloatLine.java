package cdp.test.automation.pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MessageFloatLine {
    SelenideElement message = $(".message-fade");

    public String getMessageTest() {
        return message.shouldBe(Condition.visible).getText();
    }
}
