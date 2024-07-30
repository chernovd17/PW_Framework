package management.selenium.web_context.pages.airbnb;

import management.selenium.elements.Element;
import management.selenium.web_context.containers.ProfileContainer;
import management.selenium.web_context.containers.pop_ups.SignUpPopUp;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseAirbnbPage extends BasePage {

    private final Element profileElement;

    private ProfileContainer profileContainer;
    public BaseAirbnbPage(String name, WebDriver driver) {
        super(name, driver);
        profileElement = new Element("Profile element", this, By.cssSelector("[data-tooltip-anchor-id='headernav-profile-button']"));
        profileContainer = new ProfileContainer(this);
    }

    private ProfileContainer openProfileContainerIfNeeded() {
        if(profileContainer.notVisible()) {
            STEP("Open Profile Container");
            profileElement.click();
            profileContainer.waitForOpening();
        }
        return profileContainer;
    }

    public SignUpPopUp openSignUpPopUp() {
        return openProfileContainerIfNeeded().signUp();
    }
}
