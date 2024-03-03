package example;

import base_tests.NewBaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest3 extends NewBaseTest {

    @Test(testName = "TestEx3", description = "descr3")
    public void test() throws InterruptedException {

        Validation.verifyTrue(true, "Test3 vtrue");
        Validation.verifyTrue(false, "Test3 vfalse");

        Validation.assertEquals(true,  false,"Test3 atrue");
        Validation.assertTrue(false, "Test3 afalse");

        addTestFinalStatusToLog();
    }
}
