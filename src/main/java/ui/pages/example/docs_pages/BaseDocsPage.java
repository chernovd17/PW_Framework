package ui.pages.example.docs_pages;

import com.microsoft.playwright.BrowserContext;
import ui.containers.example.DocsLeftSideElementContainer;
import ui.pages.BasePage;
import ui.pages.example.BasePwPage;

public abstract class BaseDocsPage extends BasePwPage {

    protected static final String PAGES_ARTICLE = "Pages";
    protected DocsLeftSideElementContainer docsLeftSideBlock = new DocsLeftSideElementContainer(this);

    public BaseDocsPage(String name, BrowserContext context) {
        super(name, context);
    }

    public BaseDocsPage(String name, BasePage basePage) {
        super(name, basePage);
    }

    public PwPagesPage openPagesArticle(){
        STEP("Open Pages Article");
        docsLeftSideBlock.clickOption(PAGES_ARTICLE);
        PwPagesPage pwPagesPage = new PwPagesPage(this);
        pwPagesPage.waitForOpening();
        return pwPagesPage;
    }


}
