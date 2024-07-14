package example;

import base_tests.BaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest1 extends BaseTest {

    @Test(testName = "Example Test", description = "Test Description")
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(Sessions.getCurrentSession().getBrowserManager().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        PwInstallationPage pwInstallationPage = playwrightMainPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();
        pwPagesPage.waitForOpening();

        Validation.verifyTrue(true, "Positive boolean non-critical verification");
        Validation.verifyEquals("Test", "Test", "Positive non-critical equals verification");
        Validation.assertEquals(123, 123, "Positive critical equals verification");

        Validation.verifyEquals("Actual Val", "Exp Value", "Negative non-critical equals verification");
        Validation.assertTrue(false, "Negative boolean critical equals verification");

        generateTestFinalStatus();
    }
}
