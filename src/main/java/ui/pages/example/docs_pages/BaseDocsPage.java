package ui.pages.example.docs_pages;

import com.microsoft.playwright.BrowserContext;
import ui.blocks.example.DocsLeftSideBlock;
import ui.pages.BasePage;
import ui.pages.example.BasePwPage;

public abstract class BaseDocsPage extends BasePwPage {

    protected static final String PAGES_ARTICLE = "Pages";
    protected DocsLeftSideBlock docsLeftSideBlock = new DocsLeftSideBlock(this);

    public BaseDocsPage(String name, BrowserContext context) {
        super(name, context);
    }

    public BaseDocsPage(String name, BasePage basePage) {
        super(name, basePage);
    }
    public PwPagesPage openPagesArticle(){
        docsLeftSideBlock.clickOption(PAGES_ARTICLE);
        PwPagesPage pwPagesPage = new PwPagesPage(this);
        pwPagesPage.waitForOpening();
        return pwPagesPage;
    }


}
