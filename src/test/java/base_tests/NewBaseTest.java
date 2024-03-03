package base_tests;

import LOGGER.entities.LogLevels;
import LOGGER.entities.ReportRow;
import LOGGER.entities.SuiteInfo;
import LOGGER.entities.TestInfo;
import LOGGER.withlog4j2.GlobalLoggerSession;
import LOGGER.withlog4j2.TestLogger;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import helpers.FileSystemHelper;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import management.playwright.PlaywrightSession;
import management.playwright.run_management.Sessions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class NewBaseTest {

    protected static SuiteInfo suiteInfo;

    /** in TestNG7.9.0 Native Injections for BeforeSuite is disabled
     * **/
    @BeforeSuite(alwaysRun = true)
    public synchronized void beforeSuite(ITestContext context) {
        suiteInfo = new SuiteInfo(context);
    }

    @BeforeMethod
    protected void launch(ITestContext context, Method method, Object[] params){
        Test annotation = method.getAnnotation(Test.class);

        Sessions.createSession(annotation);
        getBrowserManager().navigate(ExampleEnvironment.get().getAppUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, Method method, Object[] params) {
        Sessions.killCurrentSession();
    }

    protected TestLogger getLogger() {
        return Sessions.getCurrentSession().getLoggerSession();
    }

    protected BrowserManager getBrowserManager() {
        return Sessions.getCurrentSession().getBrowserManager();
    }


    @AfterSuite(alwaysRun = true)
    protected void closeAllSession() {
        Sessions.killAllSessions();
        System.out.println(suiteInfo.toString());
    }


    protected void generateTestFinalStatus(){
        TestInfo testInfo = getLogger().getTestInfo();

        List<ReportRow> rows = testInfo.getReportRows();
        List<ReportRow> failedRows = rows.stream().filter(
                row -> row.getLogLevels().equals(LogLevels.FAIL)).toList();

        int countOfFailed = failedRows.size();
        if(countOfFailed > 0){
            String concatenatedString = failedRows.stream().
                    map(ReportRow::getInfo).collect(Collectors.joining("\n"));
            getLogger().FAIL("Test is finished with " + countOfFailed + " UNCRITICAL errors:\n"
                    + concatenatedString, getBrowserManager().makeScreenshot());
            throw new AssertionError("Test is finished with " + countOfFailed + " UNCRITICAL errors:\n"
                    + concatenatedString);
        } else
            getLogger().SYSTEM("Test finished SUCCESSFULLY", getBrowserManager().makeScreenshot());
    }

}