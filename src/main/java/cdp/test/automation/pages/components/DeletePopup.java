package cdp.test.automation.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DeletePopup {
    SelenideElement deleteBtn = $(".rp-btn-danger");

    public MessageFloatLine clickDeleteBtn() {
        deleteBtn.shouldBe(visible);
        deleteBtn.click();
        return new MessageFloatLine();
    }
}
