package LOGGER.withlog4j2;

import LOGGER.entities.LogLevels;
import LOGGER.entities.SuiteInfo;
import helpers.FileSystemHelper;
import lombok.Getter;
import management.environment.LoggerEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;


public class GlobalLoggerSessionParallel {

    @Getter
    private SuiteInfo suiteInfo;

    private static GlobalLoggerSessionParallel session = null;

    private static ThreadLocal<TestLogger> testLoggerSessions = new ThreadLocal<>();

    public static GlobalLoggerSessionParallel getSession(){
        if(session == null)
            throw new IllegalStateException("GLOBAL LOGGER IS NOT INITIALIZED");
        return session;
    }

    public static GlobalLoggerSessionParallel initLoggerAndSuiteInfo(ITestContext context){
        session = new GlobalLoggerSessionParallel();
        session.suiteInfo = new SuiteInfo(context);
        FileSystemHelper.createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerDirectory());
        FileSystemHelper.createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerScreenshotsDirectory());
        return session;
    }

    public static TestLogger getTestLogger() {
        if (testLoggerSessions.get() == null) {
            throw new IllegalStateException("TEST LOGGER IS NOT INITIALIZED");
        }
        return testLoggerSessions.get();
    }

    public void resetTestLoggerSession() {
        if(testLoggerSessions.get() != null) {
            testLoggerSessions.remove();
        }
    }


}
