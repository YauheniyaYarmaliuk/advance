package cdp.test.automation.utils;

import com.codeborne.selenide.ElementsCollection;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomDataGen {
    public static String getRandomFromArrayList(String[] arrayList) {
        return arrayList[new Random().nextInt(arrayList.length)];
    }

    public static void clickThreeRndValFromCollection(ElementsCollection elementCol) {
        for (int i = 0; i < 3; i++) {
            int n = new Random().ints(0, (elementCol.size())).limit(1).findFirst().getAsInt();
            elementCol.get(n).click();
        }
    }
}
