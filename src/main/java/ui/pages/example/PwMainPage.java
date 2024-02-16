package ui.pages.example;

import com.microsoft.playwright.BrowserContext;
import ui.elements.Element;
import ui.elements.locator.Loc;


import java.time.Duration;

public class PwMainPage extends BasePwPage {

    private Element getStartedElement = new Element("Post", this, Loc.xpath(".//a[@class='getStarted_Sjon']"));

    public PwMainPage(BrowserContext context) {
        super("Playwright Main Page", context);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return getStartedElement.waitForVisible(duration);
    }
}
