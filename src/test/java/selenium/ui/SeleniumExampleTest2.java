package selenium.ui;

import management.selenium.sessions_management.SeleniumSessions;
import management.selenium.web_context.containers.pop_ups.SignUpPopUp;
import management.selenium.web_context.pages.airbnb.AirBnbMainPage;
import management.selenium.web_context.pages.airbnb.CanMoreVacationPage;
import org.testng.annotations.Test;

public class SeleniumExampleTest2 extends BaseSeleniumTest {

    private String searchValue = "Suomi";
    @Test(testName = "Test Selenium2", description = "Selenium test")
    public void test2(){
        AirBnbMainPage airBnbMainPage = new AirBnbMainPage(SeleniumSessions.getCurrentSession().getBrowserManager().getDriver());
        airBnbMainPage.waitAndVerifyPageIsOpened();

        SignUpPopUp signUpPopUp = airBnbMainPage.openSignUpPopUp();
        signUpPopUp.waitForOpening();

        airBnbMainPage = signUpPopUp.closeToPage(AirBnbMainPage.class);
        airBnbMainPage.verifyCanMoreVisible();
        CanMoreVacationPage canMoreVacationPage = airBnbMainPage.openCanMoreVacationPage();
        canMoreVacationPage.waitAndVerifyPageIsOpened();

        generateTestFinalStatus();
    }
}
