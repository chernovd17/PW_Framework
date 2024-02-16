package ui.blocks.example;

import com.microsoft.playwright.BrowserContext;
import ui.IWebContext;
import ui.blocks.BaseBlock;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.docs_pages.PwInstallationPage;

public class PwHeaderBlock extends BaseBlock {

    private Element docsElement = new Element("Docs", this, Loc.xpath(".//a[text()='Docs']"));
    private Element gitIcon = new Element("Git Icon", this, Loc.css("a[aria-label='GitHub repository']"));

    public PwHeaderBlock(IWebContext parent) {
        super("", parent, Loc.css("nav[aria-label='Main']"));
    }

    public PwInstallationPage openDocsPage() {
        docsElement.click();
        PwInstallationPage pwInstallationPage = new PwInstallationPage(this.getPwPage().context());
        pwInstallationPage.waitForOpening();
        return pwInstallationPage;
    }

    public GitHubRepositoryPage openGitHubRepository() {

        GitHubRepositoryPage gitHubRepositoryPage = new GitHubRepositoryPage(
                this.getPage().getPwContext().waitForPage(
                        () -> gitIcon.click()
                )
        );
        gitHubRepositoryPage.waitForOpening();
        return gitHubRepositoryPage;
    }
}
