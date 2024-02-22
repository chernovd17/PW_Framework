package ui.pages.example;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ui.containers.example.PwHeaderElementContainer;
import ui.pages.BasePage;
import ui.pages.example.docs_pages.PwInstallationPage;


public abstract class BasePwPage extends BasePage {

    private PwHeaderElementContainer pwHeaderBlock = new PwHeaderElementContainer(this);

    public BasePwPage(String name, BrowserContext context) {
        super(name, context);
    }

    public BasePwPage(String name, BasePage basePage) {
        super(name, basePage.getPwPage());
    }

    public BasePwPage(String name, Page page) {
        super(name, page);
    }

    public PwInstallationPage openDocsPage(){
        return pwHeaderBlock.openDocsPage();
    }

    public GitHubRepositoryPage openGitHubRepository(){
        STEP("OPEN GIT HUB REPOSITORY");
        return pwHeaderBlock.openGitHubRepository();
    }


}
