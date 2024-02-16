package example;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.PwMainPage;
import ui.pages.example.docs_pages.PwInstallationPage;
import ui.pages.example.docs_pages.PwPagesPage;


public class ExampleTest2 extends BaseExampleTest {

    private static final String EXPECTED_VERSION = "v1.41.2";
    @Test
    public void test(){

        PwMainPage playwrightMainPage = new PwMainPage(browserManager.getBrowser().contexts().get(0));
        playwrightMainPage.waitForOpening();

        GitHubRepositoryPage gitHubRepositoryPage = playwrightMainPage.openGitHubRepository();

        String actual = gitHubRepositoryPage.getLastVersion();
        Assert.assertEquals(actual, EXPECTED_VERSION);

        gitHubRepositoryPage = new GitHubRepositoryPage(browserManager.closeLastTab());
        PwInstallationPage pwInstallationPage = gitHubRepositoryPage.openDocsPage();

        PwPagesPage pwPagesPage = pwInstallationPage.openPagesArticle();
    }
}
