package management.selenium.web_context.pages.testLogin;


import management.selenium.elements.Button;
import management.selenium.web_context.IWebContext;
import management.selenium.web_context.containers.pop_ups.BasePopUp;
import org.openqa.selenium.By;

public class CookiesPopUp extends BasePopUp {

    private final Button zazwolBtn;

    public CookiesPopUp(IWebContext parent) {
        super("Cookies Pop Up", parent, By.cssSelector("div.ne-piwik-cookies__container"));
        zazwolBtn = new Button("Zazwol Btn", this, By.cssSelector("button.ne-piwik-cookies__btn-save-all"));
    }

    public LoginPage zazwol(){
        zazwolBtn.click();
        waitForClose();
        return new LoginPage(getDriver());
    }

}
