package base_tests.selenium.api;

import management.selenium.rest_api.BaseRestAssuredConfig;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite
    public void initRestAssured(){
        BaseRestAssuredConfig.init();
    }
}
