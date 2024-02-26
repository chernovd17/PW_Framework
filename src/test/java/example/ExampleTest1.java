package example;

import helpers.Validation;
import org.testng.annotations.Test;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest1 extends BaseExampleTest {

    @Test(testName = "TestNName", description = "descr")
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(browserManager.get().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        PwInstallationPage pwInstallationPage = playwrightMainPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();
        pwPagesPage.waitForOpening();

        Validation.verifyTrue(true, "Test1");
        Validation.verifyEquals("Test", "Test", "Test2");
        Validation.verifyEquals(123, 1234, "Test3");

        Validation.assertEquals("Test", "Test", "Test2");
        Validation.assertEquals("asd", "asd", "Test4");

        generateTestFinalStatus();
    }
}
