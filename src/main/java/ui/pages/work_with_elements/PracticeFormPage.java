package ui.pages.work_with_elements;

import com.microsoft.playwright.BrowserContext;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.time.Duration;

public class PracticeFormPage extends BasePage {

    private final Element titleElement = new Element("Post", this, Loc.xpath(".//h1[text()='Practice Form']"));

    public PracticeFormPage(String name, BrowserContext context) {
        super("Practice Form Page", context);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return titleElement.waitForVisible(duration);
    }
}
