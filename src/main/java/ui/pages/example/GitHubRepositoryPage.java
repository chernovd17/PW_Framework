package ui.pages.example;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import ui.elements.Element;
import ui.elements.locator.Loc;

import java.time.Duration;

public class GitHubRepositoryPage extends BasePwPage {

    private final Element linkTitle = new Element("LinkTitle", this, Loc.xpath(".//a[text()='playwright-java']"));
    private final Element lastVersionElement = new Element("Last Version Element", this,
            Loc.xpath(".//div[@class='BorderGrid-cell' and .//*[contains(text(), 'Release')]]//div[./span[contains(text(), 'Latest')]]/span[1]"));

    public GitHubRepositoryPage(BrowserContext context) {
        super("Hit Hub Repository Page", context);
    }

    public GitHubRepositoryPage(Page page) {
        super("Hit Hub Repository Page", page);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return linkTitle.waitForVisible(duration);
    }

    public String getLastVersion(){
        return lastVersionElement.getText();
    }
}
