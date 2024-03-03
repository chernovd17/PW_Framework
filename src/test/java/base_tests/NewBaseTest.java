package base_tests;

import LOGGER.entities.SuiteInfo;
import LOGGER.withlog4j2.TestLogger;
import helpers.FileSystemHelper;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import management.playwright.run_management.Sessions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class NewBaseTest {

    protected static SuiteInfo suiteInfo;

    /** in TestNG7.9.0 Native Injections for BeforeSuite became disabled
     * **/
    @BeforeSuite(alwaysRun = true)
    public synchronized void beforeSuite(ITestContext context) {
        suiteInfo = SuiteInfo.initSuite(context);
        FileSystemHelper.initLoggerDirectoryIfNeeded();//todo maybe add it before each screenshot making
    }

    @BeforeMethod
    protected void launch(ITestContext context, Method method, Object[] params){
        Test annotation = method.getAnnotation(Test.class);

        Sessions.createSession(annotation);
        getBrowserManager().navigate(ExampleEnvironment.get().getAppUrl());
        getLogger().getTestInfo().makeReportRowsActive();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext context, Method method, Object[] params) {
        if(!getLogger().getTestInfo().isCaseFullyCompleted())
            getLogger().addTestFinalStatusToLog(getBrowserManager().makeScreenshot());
        getLogger().getTestInfo().makeAfterTestRowsActive();
        suiteInfo.addTestInfo(getLogger().getTestInfo());
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

    //only as last step for any testMethod
    protected void addTestFinalStatusToLog(){
        getLogger().addTestFinalStatusToLog(getBrowserManager().makeScreenshot());
    }

}