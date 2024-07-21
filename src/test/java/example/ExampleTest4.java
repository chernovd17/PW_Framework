package example;

import base_tests.BaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.PwMainPage;


public class ExampleTest4 extends BaseTest {

    @Test(testName = "Example Test4", description = "Negative verification")
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(Sessions.getCurrentSession().getBrowserManager().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        Validation.verifyTrue(false, "Positive boolean non-critical verification");

        generateTestFinalStatus();
    }
}