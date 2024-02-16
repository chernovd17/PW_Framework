package ui.pages.example.docs_pages;

import com.microsoft.playwright.BrowserContext;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.time.Duration;

public class PwPagesPage extends BaseDocsPage {

    private final Element installationHeader = new Element("Installation Header Element", this,
            Loc.xpath(".//h1[text()='Pages']"));

    public PwPagesPage(BrowserContext context) {
        super("Docs Page", context);
    }

    public PwPagesPage(BasePage basePage) {
        super("Docs Page", basePage);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return docsLeftSideBlock.waitForOpening(duration);
    }

}
