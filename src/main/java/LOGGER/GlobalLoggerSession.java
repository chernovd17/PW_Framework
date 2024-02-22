package LOGGER;

import LOGGER.info.SuiteInfo;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class GlobalLoggerSession {

    private static GlobalLoggerSession session = null;

    private SuiteInfo suiteInfo;

    private static TestLogger testLoggerSession;

    public static GlobalLoggerSession getSession(){
        if(session == null)
            throw new IllegalStateException("GLOBAL LOGGER IS NOT INITIALIZED");
        return session;
    }

    public static void initLoggerAndSuiteInfo(ITestContext context){
        session = new GlobalLoggerSession();
        session.suiteInfo = new SuiteInfo(context);
    }

    public void initTestLoggerSession(Test testAnnotation) {
        if(testLoggerSession == null)
            testLoggerSession = new TestLogger(testAnnotation);
    }

    public TestLogger getTestLogger() {
        if(testLoggerSession == null)
            throw new IllegalStateException("TEST LOGGER IS NOT INITIALIZED");
        return testLoggerSession;
    }

    public void resetTestLoggerSession() {
        testLoggerSession = null;
    }
}
