package example;

import helpers.Validation;
import org.testng.annotations.Test;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest2 extends BaseExampleTest {

    private static final String EXPECTED_VERSION = "v1.41.2";
    @Test
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(browserManager.get().getBrowser().contexts().getFirst());
        playwrightMainPage.waitForOpening();

        GitHubRepositoryPage gitHubRepositoryPage = playwrightMainPage.openGitHubRepository();

        String actual = gitHubRepositoryPage.getLastVersion();
        Validation.verifyTrue(actual.equals(EXPECTED_VERSION), "Verify if Version is as expected");
        //Validation.verifyTrue(false, "just false");

        gitHubRepositoryPage = new GitHubRepositoryPage(browserManager.get().closeLastTab());
        PwInstallationPage pwInstallationPage = gitHubRepositoryPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();

        generateTestFinalStatus();
    }
}
