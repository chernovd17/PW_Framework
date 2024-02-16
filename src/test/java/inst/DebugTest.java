package inst;


import inst.entities.User;
import inst.ui.blocks.NotificationDialog;
import inst.ui.pages.LoginPage;
import inst.ui.pages.application_pages.HomePage;
import org.testng.annotations.Test;

public class DebugTest extends BaseInstTest {

    @Test(testName = "Inst Example")
    public void test() {

        LoginPage loginPage = new LoginPage(getCurrentPwPage());
        loginPage.waitForOpening();

        HomePage homePage = loginPage.loginWithAllowingCookies(User.getDefaultUser());
        homePage.waitForOpening();

        NotificationDialog notificationDialog = new NotificationDialog(homePage);
        notificationDialog.waitForOpening();

        notificationDialog.clickNotNow();

        System.out.println("asdas");

    }

}
