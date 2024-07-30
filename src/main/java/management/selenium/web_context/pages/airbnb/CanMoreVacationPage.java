package management.selenium.web_context.pages.airbnb;

import management.selenium.elements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CanMoreVacationPage extends BaseAirbnbPage {

    private final Element sloganElement;

    public CanMoreVacationPage(WebDriver driver) {
        super("Can more Vacation Page", driver);
        sloganElement = new Element("Slogan", this, By.xpath(".//a[text()='Airbnb your place']"));
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return sloganElement.waitForVisible(duration);
    }
}
