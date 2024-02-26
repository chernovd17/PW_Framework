package base_tests;

import LOGGER.entities.LogLevels;
import LOGGER.entities.ReportRow;
import LOGGER.entities.TestInfo;
import LOGGER.withlog4j2.*;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import helpers.FileSystemHelper;
import management.environment.DefaultEnvironment;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import management.playwright.PlaywrightSession;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {

    protected ThreadLocal<BrowserManager> browserManager = new ThreadLocal<>();
    protected ThreadLocal<GlobalLoggerSession> logger = new ThreadLocal<>();;
    @BeforeMethod
    protected void launch(ITestContext context, Method method, Object[] params){
        Test annotation = method.getAnnotation(Test.class);
        if(logger.get() == null)
            logger.set(GlobalLoggerSession.initLoggerAndSuiteInfo(context)); //GlobalLoggerSession.initLoggerAndSuiteInfo(context);
        else
            getLogger().resetTestLoggerSession();
        getLogger().initTestLoggerSession(annotation);
        getLogger().SYSTEM("Start");
        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel("chrome")
                .setArgs(args);
        BrowserManager localBrowserManager = BrowserManager.createNew(ExampleEnvironment.get().getBrowserName(), type);
        browserManager.set(localBrowserManager);
        browserManager.get().navigate(ExampleEnvironment.get().getAppUrl());

    }

    @AfterSuite(alwaysRun = true)
    protected void closeAllSession(){
        browserManager.get().closeBrowser();
        PlaywrightSession.getInstance().close();
    }

    protected Page getCurrentPwPage(){
        return browserManager.get().getBrowser().contexts().get(0).pages().get(0);
    }

    protected GlobalLoggerSession getLogger() {
        return logger.get();
    }

    /** in TestNG7.9.0 Native Injections for BeforeSuite is disabled
     * **/
    @BeforeSuite(alwaysRun = true)
    public synchronized void beforeSuite(ITestContext context) {
        //if(logger.get() == null)
        logger.set(GlobalLoggerSession.initLoggerAndSuiteInfo(context)); //GlobalLoggerSession.initLoggerAndSuiteInfo(context);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, Method method, Object[] params) {
        getLogger().getTestLogger().getTestInfo().setEndDateTime(LocalDateTime.now());
        browserManager.get().closeBrowser();
        getLogger().resetTestLoggerSession();
    }


    protected void generateTestFinalStatus(){
        TestInfo testInfo = getLogger().getTestLogger().getTestInfo();

        List<ReportRow> rows = testInfo.getReportRows();
        List<ReportRow> failedRows = rows.stream().filter(
                row -> row.getLogLevels().equals(LogLevels.FAIL)).toList();

        int countOfFailed = failedRows.size();
        if(countOfFailed > 0){
            String concatenatedString = failedRows.stream().
                    map(ReportRow::getInfo).collect(Collectors.joining("\n"));
            getLogger().FATAL("Test is finished with " + countOfFailed + " UNCRITICAL errors:\n "
                    + concatenatedString, makeScreenshot());
        } else
            getLogger().SYSTEM("Test finished SUCCESSFULLY", makeScreenshot());
    }


    public File makeScreenshot(){

        Page page = browserManager.get().getContext().pages().getLast();//now I don't know how to get active window, so will take last
        byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return FileSystemHelper.createScreenshotFile(buffer);
    }

}
