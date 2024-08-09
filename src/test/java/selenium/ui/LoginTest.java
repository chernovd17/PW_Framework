package selenium.ui;

import management.selenium.sessions_management.SeleniumSessions;
import management.selenium.web_context.pages.testLogin.CookiesPopUp;
import management.selenium.web_context.pages.testLogin.LoginPage;
import management.selenium.web_context.pages.testLogin.LogowaniePage;
import management.selenium.web_context.pages.testLogin.MainApplicationPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseSeleniumTest {

    private final String username = "StudentNE@wp.pl";
    private final String password = "NowaEra2019";
    @Test
    public void test(){
        LoginPage loginPage = new LoginPage(SeleniumSessions.getCurrentSession().getBrowserManager().getDriver());
        loginPage.waitForOpening();

        CookiesPopUp cookiesPopUp = new CookiesPopUp(loginPage);
        cookiesPopUp.waitForOpening();
        loginPage = cookiesPopUp.zazwol();
        loginPage.waitForOpening();

        LogowaniePage logowaniePage = loginPage.clickZaloguj();
        logowaniePage.waitForOpening();

        MainApplicationPage mainApplicationPage = logowaniePage.logIn(username, password);
        mainApplicationPage.waitAndVerifyPageIsOpened();

        generateTestFinalStatus();
    }
}
