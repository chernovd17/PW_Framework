package management.selenium.web_context.pages.testLogin;

import management.selenium.elements.Button;
import management.selenium.elements.Element;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final Button zalogujBtn;
    public LoginPage(WebDriver driver) {
        super("Login Page", driver);
        zalogujBtn = new Button("Zaloguj sie", this, By.xpath(".//button[.//span[contains(text(), 'Zaloguj się')]]"));
    }

    @Override
    public boolean waitForOpening(Duration duration) {
        return zalogujBtn.waitForVisible(duration);
    }

    public LogowaniePage clickZaloguj(){
        zalogujBtn.click();
        return new LogowaniePage(getDriver());
    }
}
