package management.selenium.web_context.pages.airbnb;

import management.selenium.elements.Element;
import management.selenium.elements.Link;
import management.selenium.helpers.Verification;
import management.selenium.web_context.containers.SearchFieldContainer;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class AirBnbMainPage extends BaseAirbnbPage {

    private final Element header;
    private final Element pastExperience;
    private final Link canmore;
    private final SearchFieldContainer searchField;

    public AirBnbMainPage(WebDriver driver) {
        super("Airbnb Main Page", driver);
        header = new Element("Header", this, By.cssSelector("div[data-testid='QA_EXPLORE_HEADER']"));
        pastExperience = new Element("Past Experience Title", this, By.xpath(".//h2[text()='Past experiences']"));
        canmore = new Link("Can more", this, By.xpath(".//a[./span[text()='Canmore']]"));
        searchField = new SearchFieldContainer(this);
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return header.waitForVisible(duration);
    }

    public String getWhereSearchFieldValue() {
        return searchField.getWhereSearchField();
    }

    public void setWhereSearchFieldValue(String where) {
        searchField.setWhereSearchField(where);
    }

    public SearchingResultPage clickSearchButtonWithRedirect(){
        searchField.clickSearchButton();
        return new SearchingResultPage(getDriver());
    }

    public void verifyCanMoreVisible(){
        Verification.verifyTrue(canmore.visible(), String.format("%s link should be visible", canmore.getLogicalName()));
    }

    public CanMoreVacationPage openCanMoreVacationPage(){
        STEP("Open Canmore link");
        canmore.click();
        return new CanMoreVacationPage(getDriver());
    }

}
