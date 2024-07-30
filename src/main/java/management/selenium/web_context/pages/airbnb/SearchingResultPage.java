package management.selenium.web_context.pages.airbnb;

import management.selenium.elements.Element;
import management.selenium.elements.Select;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SearchingResultPage extends BaseAirbnbPage {

    private final Element nationalParksElement;
    public SearchingResultPage(WebDriver driver) {
        super("Searching Result Page", driver);
        nationalParksElement = new Element("National Park", this, By.xpath(".//span[text()='National parks']"));
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return nationalParksElement.waitForVisible(duration);
    }
}
