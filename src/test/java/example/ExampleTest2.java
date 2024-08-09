package example;

import base_tests.BaseTest;
import helpers.Validation;
import management.playwright.run_management.Sessions;
import org.testng.annotations.Test;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.docs_pages.PwPagesPage;

import java.time.Duration;


public class ExampleTest2 extends BaseTest {

    private static final String EXPECTED_VERSION = "v1.45.1";
    @Test(testName = "Expected version verification", description = "Verify if Playwright version is as expected")
    public void test() {

        PwMainPage playwrightMainPage = new PwMainPage(Sessions.getCurrentSession().getBrowserManager().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        GitHubRepositoryPage gitHubRepositoryPage = playwrightMainPage.openGitHubRepository();

        String actual = gitHubRepositoryPage.getLastVersion();
        Validation.verifyEquals(actual, EXPECTED_VERSION, "Verify if Version is as expected");

        gitHubRepositoryPage = new GitHubRepositoryPage(Sessions.getCurrentSession().getBrowserManager().closeLastTab());
        PwInstallationPage pwInstallationPage = gitHubRepositoryPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();

        Validation.verifyTrue(pwPagesPage.waitForOpening(Duration.ZERO), "Verify if Playwright Page is Opened");

        generateTestFinalStatus();
    }
}
