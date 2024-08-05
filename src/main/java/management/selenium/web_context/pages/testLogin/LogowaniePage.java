package management.selenium.web_context.pages.testLogin;

import management.selenium.elements.Button;
import management.selenium.elements.Input;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LogowaniePage extends BasePage {

    private final Input emailInput;
    private final Input passwordInput;
    private final Button loginBtn;
    public LogowaniePage( WebDriver driver) {
        super("Logowanie Page", driver);
        emailInput = new Input("Email input field", this, By.id("user-form-login-email"));
        passwordInput = new Input("Password input field", this, By.id("user-form-login-password"));
        loginBtn = new Button("Login Btn", this, By.id("user-form-login-submit"));
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return emailInput.waitForVisible(duration);
    }

    public MainApplicationPage logIn(String email, String password){
        emailInput.setText(email);
        passwordInput.setText(password);
        loginBtn.click();
        return new MainApplicationPage(getDriver());
    }
}
