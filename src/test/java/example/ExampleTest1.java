package example;

import org.testng.annotations.Test;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest1 extends BaseExampleTest {

    @Test
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(browserManager.getBrowser().contexts().get(0));
        playwrightMainPage.waitForOpening();

        PwInstallationPage pwInstallationPage = playwrightMainPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();
        pwPagesPage.waitForOpening();
    }
}
