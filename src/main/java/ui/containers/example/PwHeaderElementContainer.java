package ui.containers.example;

import helpers.Validation;
import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.example.GitHubRepositoryPage;
import ui.pages.example.docs_pages.PwInstallationPage;

public class PwHeaderElementContainer extends BaseElementContainer {

    private Element docsElement = new Element("Docs", this, Loc.xpath(".//a[text()='Docs']"));
    private Element gitIcon = new Element("Git Icon", this, Loc.css("a[aria-label='GitHub repository']"));

    public PwHeaderElementContainer(IWebContext parent) {
        super("", parent, Loc.css("nav[aria-label='Main']"));
    }

    public PwInstallationPage openDocsPage() {
        Validation.verifyTrue(docsElement.visible(), "Docs element is not displayed");
        docsElement.click();
        PwInstallationPage pwInstallationPage = new PwInstallationPage(this.getPwPage().context());
        pwInstallationPage.waitForOpening();
        return pwInstallationPage;
    }

    public GitHubRepositoryPage openGitHubRepository() {

        GitHubRepositoryPage gitHubRepositoryPage = new GitHubRepositoryPage(
                this.getPage().getPwPage().context().waitForPage(
                        () -> gitIcon.click())
        );
        gitHubRepositoryPage.waitForOpening();
        return gitHubRepositoryPage;
    }
}
