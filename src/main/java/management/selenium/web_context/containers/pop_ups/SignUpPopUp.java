package management.selenium.web_context.containers.pop_ups;

import management.selenium.elements.Button;
import management.selenium.elements.Element;
import management.selenium.helpers.Verification;
import management.selenium.web_context.IWebContext;
import management.selenium.web_context.pages.airbnb.BaseAirbnbPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class SignUpPopUp extends BasePopUp {

    private final Element closeElement;
    private final Button continueWithFacebookButton;
    private final Button continueWithGoogleButton;
    private final Button continueWithAppleButton;
    private final Button continueWithEmailButton;

    public SignUpPopUp(IWebContext parent) {
        super("Sign Up PopUp", parent, By.cssSelector("div[aria-label='Log in']"));
        closeElement = new Element("Cross", this, By.cssSelector("[aria-label='Close']"));
        continueWithFacebookButton = new Button("Continue with Facebook", this, By.cssSelector("button[data-testid='social-auth-button-facebook']"));
        continueWithGoogleButton = new Button("Continue with Google", this, By.cssSelector("button[data-testid='social-auth-button-google']"));
        continueWithAppleButton = new Button("Continue with Apple", this, By.cssSelector("button[data-testid='social-auth-button-apple']"));
        continueWithEmailButton = new Button("Continue with Email", this, By.cssSelector("button[data-testid='social-auth-button-email']"));
    }

    public void verifyAllElementsAreVisible() {
        Verification.verifyTrue(continueWithFacebookButton.visible(), String.format("Verify if %s is visible", continueWithFacebookButton.getLogicalName()));
        Verification.verifyTrue(continueWithGoogleButton.visible(), String.format("Verify if %s is visible", continueWithGoogleButton.getLogicalName()));
        Verification.verifyTrue(continueWithAppleButton.visible(), String.format("Verify if %s is visible", continueWithAppleButton.getLogicalName()));
        Verification.verifyTrue(continueWithEmailButton.visible(), String.format("Verify if %s is visible", continueWithEmailButton.getLogicalName()));
    }

    public void close(){
        STEP(String.format("Close %s", getContainerAsElement().getLogicalName()));
        closeElement.click();
        waitForClose();
    }

    public <T extends BaseAirbnbPage> T closeToPage(Class<T> pageClass){
        close();
        return PageFactory.initElements(getDriver(), pageClass);
    }
}
