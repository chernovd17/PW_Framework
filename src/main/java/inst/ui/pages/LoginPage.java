package inst.ui.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import inst.entities.User;
import inst.ui.blocks.AllowCookiesDialog;
import ui.elements.Button;
import ui.elements.Input;
import ui.elements.locator.Loc;
import ui.pages.BasePage;
import inst.ui.pages.application_pages.HomePage;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final Input userNameEdit = new Input("Username", this, Loc.name("username"));
    private final Input passwordEdit = new Input("Password", this, Loc.name("password"));
    private final Button loginBtn = new Button("Login", this, Loc.css("button[type=submit]"));

    public LoginPage(BrowserContext context) {
        super("Login Page", context);
    }

    public LoginPage(Page pwPage) {
        super("Login Page", pwPage);
    }

    @Override
    public boolean waitForOpening(Duration timeout) {
        return userNameEdit.waitForVisible(timeout);
    }

    public HomePage login(String login, String password){
        userNameEdit.typeText(login);
        passwordEdit.typeText(password);
        loginBtn.click();
        return new HomePage(getPwPage());
    }

    public HomePage login(User user){
        return login(user.getUsername(), user.getPassword());
    }

    public HomePage loginWithAllowingCookies(User user){
        AllowCookiesDialog allowCookiesDialog = new AllowCookiesDialog(this);
        allowCookiesDialog.waitForOpening(Duration.ZERO);

        allowCookiesDialog.allowCookies();

        waitForOpening();
        return login(user);
    }
}

