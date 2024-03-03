package example;

import base_tests.BaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest1 extends BaseTest {

    @Test(testName = "TestNName", description = "descr")
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(Sessions.getCurrentSession().getBrowserManager().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        PwInstallationPage pwInstallationPage = playwrightMainPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();
        pwPagesPage.waitForOpening();

        Validation.verifyTrue(true, "Test11");
        Validation.verifyEquals("Test", "Test", "Test12");
        Validation.verifyEquals(123, 1234, "Test13");

        Validation.assertEquals("Test", "Test", "Test14");

        addTestFinalStatusToLogInLastStep();
    }
}
