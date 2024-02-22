package base_tests;

import LOGGER.GlobalLoggerSession;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import lombok.extern.log4j.Log4j2;
import management.environment.DefaultEnvironment;
import management.playwright.BrowserManager;
import management.playwright.PlaywrightSession;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BaseTest {

    protected BrowserManager browserManager;

    @BeforeTest
    protected void launch(){
        getLogger().info("Start");
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
    protected void closeAllSession(){
        browserManager.closeBrowser();
        PlaywrightSession.getInstance().close();
        getLogger().info("Finish");
    }

    protected Page getCurrentPwPage(){
        return browserManager.getBrowser().contexts().get(0).pages().get(0);
    }

    protected Logger getLogger() {
        return log;
    }

    @BeforeSuite(alwaysRun = true)
    public synchronized void beforeSuite(ITestContext context) {
        GlobalLoggerSession.initLoggerAndSuiteInfo(context);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, Method method, Object[] params) {
        GlobalLoggerSession.getSession().resetTestLoggerSession();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest(ITestContext context, Method method, Object[] params) {
        Test annotation = method.getAnnotation(Test.class);
        GlobalLoggerSession.getSession().initTestLoggerSession(annotation);
    }

}
