package cdp.test.automation.api;

import io.restassured.builder.RequestSpecBuilder;

public class RestAssuredHelper {
    public static RequestSpecBuilder getHeader() {
        return new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json");
    }

}
