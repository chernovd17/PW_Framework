package example;

import base_tests.NewBaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest2 extends NewBaseTest {

    private static final String EXPECTED_VERSION = "v1.41.2";
    @Test
    public void test() throws InterruptedException {

        PwMainPage playwrightMainPage = new PwMainPage(Sessions.getCurrentSession().getBrowserManager().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        GitHubRepositoryPage gitHubRepositoryPage = playwrightMainPage.openGitHubRepository();

        String actual = gitHubRepositoryPage.getLastVersion();
        Validation.verifyTrue(actual.equals(EXPECTED_VERSION), "Verify if Version is as expected21");
        Validation.verifyTrue(actual.equals(EXPECTED_VERSION), "Verify if Version is as expected22");
        Validation.verifyTrue(actual.equals(EXPECTED_VERSION), "Verify if Version is as expected23");

        Thread.sleep(1231);
        Validation.verifyTrue(actual.equals(EXPECTED_VERSION), "Verify if Version is as expected24");


        //Validation.verifyTrue(false, "just false");

        gitHubRepositoryPage = new GitHubRepositoryPage(Sessions.getCurrentSession().getBrowserManager().closeLastTab());
        PwInstallationPage pwInstallationPage = gitHubRepositoryPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();

        generateTestFinalStatus();
    }
}
