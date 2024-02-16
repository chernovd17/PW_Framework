package ui.pages.example.docs_pages;

import com.microsoft.playwright.BrowserContext;
import ui.elements.Element;
import ui.elements.locator.Loc;

import java.time.Duration;

public class PwInstallationPage extends BaseDocsPage {

    private final Element installationHeader = new Element("Installation Header Element", this,
            Loc.xpath(".//h1[text()='Installation']"));

    public PwInstallationPage(BrowserContext context) {
        super("Docs Page", context);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return installationHeader.waitForVisible(duration);
    }
}
