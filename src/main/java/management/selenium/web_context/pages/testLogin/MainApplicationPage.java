package management.selenium.web_context.pages.testLogin;

import management.selenium.elements.Element;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainApplicationPage extends BasePage {

    private final Element kontoElement;
    public MainApplicationPage(WebDriver driver) {
        super("Main Page", driver);
        kontoElement = new Element("Konto Element", this, By.xpath(".//div[@class='ntm-toolbar__user-link' and .//span[contains(text(),'Konto')]]"));
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return kontoElement.waitForVisible(duration);
    }
}
