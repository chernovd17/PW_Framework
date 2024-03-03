package inst;

import base_tests.BaseTest;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import inst.entities.User;
import inst.environment.InstEnvironment;
import inst.helpers.FileHelper;
import inst.ui.pages.application_pages.HomePage;
import lombok.extern.log4j.Log4j2;
import management.environment.DefaultEnvironment;
import management.playwright.BrowserManager;
import management.playwright.PlaywrightSession;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseInstTest extends BaseTest {

    protected BrowserManager browserManager;

    /*@BeforeTest
    protected void launch() {
        getLogger().SYSTEM("Start");
        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel("chrome")
                .setArgs(args);
        browserManager = BrowserManager.createNew(DefaultEnvironment.get().getBrowserName(), type);
        browserManager.navigate(DefaultEnvironment.get().getAppUrl());
    }

    @AfterTest
    protected void closeAllSession() {
        browserManager.closeBrowser();
        PlaywrightSession.getInstance().close();
        getLogger().SYSTEM("Finish");
    }

    protected Page getCurrentPwPage() {
        return browserManager.getBrowser().contexts().get(0).pages().get(0);
    }*/
}