package cdp.test.automation.utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class JsScripts {

    public static void setItemInLocalStorage(String item, String value) {
        Selenide.sleep(1000);
        String jsCode = String.format(
                "window.localStorage.setItem('%s','%s');",
                item,
                value);
        executeJavaScript(jsCode);
    }
}
