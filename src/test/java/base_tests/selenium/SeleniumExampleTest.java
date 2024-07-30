package base_tests.selenium;

import management.selenium.helpers.Verification;
import management.selenium.sessions_management.SeleniumSessions;
import management.selenium.web_context.containers.pop_ups.SignUpPopUp;
import management.selenium.web_context.pages.airbnb.AirBnbMainPage;
import management.selenium.web_context.pages.airbnb.SearchingResultPage;
import org.testng.annotations.Test;

public class SeleniumExampleTest extends BaseSeleniumTest {

    private String searchValue = "Poland";
    @Test(testName = "Test Selenium", description = "Selenium test")
    public void test(){
        AirBnbMainPage airBnbMainPage = new AirBnbMainPage(SeleniumSessions.getCurrentSession().getBrowserManager().getDriver());
        airBnbMainPage.waitAndVerifyPageIsOpened();
        Verification.verifyTrue(airBnbMainPage.getWhereSearchFieldValue().isEmpty(), "Verify if search filed is empty by default");

        airBnbMainPage.setWhereSearchFieldValue(searchValue);

        Verification.verifyEquals(airBnbMainPage.getWhereSearchFieldValue(), searchValue, "Verify if search filed contains correct value");

        SearchingResultPage searchingResultPage = airBnbMainPage.clickSearchButtonWithRedirect();
        searchingResultPage.waitAndVerifyPageIsOpened();

        SignUpPopUp signUpPopUp = searchingResultPage.openSignUpPopUp();
        signUpPopUp.waitForOpening();

        searchingResultPage = signUpPopUp.closeToPage(SearchingResultPage.class);
        Verification.verifyTrue(signUpPopUp.visible(), "Verify if sign up pop up is closed");
        Verification.verifyTrue(!searchingResultPage.waitForOpening(), "Verify if searching result page is opened");

        generateTestFinalStatus();
    }
}
