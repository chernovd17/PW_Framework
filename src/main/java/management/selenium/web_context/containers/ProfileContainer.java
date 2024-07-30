package management.selenium.web_context.containers;

import management.selenium.elements.Link;
import management.selenium.web_context.IWebContext;
import management.selenium.web_context.containers.pop_ups.SignUpPopUp;
import org.openqa.selenium.By;

public class ProfileContainer extends BaseElementContainer {

    private final Link signUpLink;
    private final Link logInLink;

    public ProfileContainer(IWebContext parent) {
        super("Profile Container", parent, By.id("simple-header-profile-menu"));

        this.signUpLink = new Link("Sign Up",this, By.cssSelector("[data-testid='cypress-headernav-signup']"));
        this.logInLink = new Link("Log In",this, By.cssSelector("[data-testid='cypress-headernav-login']"));
    }

    public SignUpPopUp signUp() {
        STEP("Sign Up");
        signUpLink.waitForVisible();
        signUpLink.click();
        return new SignUpPopUp(this);
    }

}
