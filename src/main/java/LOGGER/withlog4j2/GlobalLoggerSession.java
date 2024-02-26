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


public class GlobalLoggerSession {

    private static Logger logger;

    private static GlobalLoggerSession session = null;

    @Getter
    private SuiteInfo suiteInfo;

    private static TestLogger testLoggerSession;

    public static GlobalLoggerSession getSession(){
        if(session == null)
            throw new IllegalStateException("GLOBAL LOGGER IS NOT INITIALIZED");
        return session;
    }

    public Logger getLogger() {
        return logger;
    }

    public static GlobalLoggerSession initLoggerAndSuiteInfo(ITestContext context){
        logger = LogManager.getLogger(CustomUIAppender.class);
        session = new GlobalLoggerSession();
        session.suiteInfo = new SuiteInfo(context);
        FileSystemHelper.createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerDirectory());
        FileSystemHelper.createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerScreenshotsDirectory());
        return session;
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

    private void log(LogLevels level, String info, File screenshot){
        logger.log(level.getLevel(), info, screenshot);
    }

    private void log(LogLevels level, String info){
        logger.log(level.getLevel(), info);
    }

    public void STEP(String info, File screenshot) {
        log(LogLevels.STEP, info, screenshot);
    }

    public void STEP(String info) {
        log(LogLevels.STEP, info);
    }

    public void ACTION(String info, File screenshot) {
        log(LogLevels.ACTION, info, screenshot);
    }

    public void ACTION(String info) {
        log(LogLevels.ACTION, info);
    }

    public void SUCCESS(String info, File screenshot) {
        log(LogLevels.SUCCESS, info, screenshot);
    }

    public void SUCCESS(String info) {
        log(LogLevels.SUCCESS, info);
    }

    public void FAIL(String info, File screenshot) {
        log(LogLevels.FAIL, info, screenshot);
    }

    public void FAIL(String info) {
        log(LogLevels.FAIL, info);
    }

    public void FATAL(String info, File screenshot) {
        log(LogLevels.FATAL, info, screenshot);
        throw new AssertionError(info);
    }

    public void FATAL(String info) {
        log(LogLevels.FATAL, info);
        throw new AssertionError(info);
    }

    public void SYSTEM(String info) {
        log(LogLevels.SYSTEM, info);
    }

    public void SYSTEM(String info, File screenshot) {
        log(LogLevels.SYSTEM, info, screenshot);
    }

    public void INFO(String info) {
        log(LogLevels.INFO, info);
    }
    public void INFO(String info, File screenshot) {
        log(LogLevels.INFO, info, screenshot);
    }

    public void UNDEFINED(String info) {
        log(LogLevels.UNDEFINED, info);
    }
    public void UNDEFINED(String info, File screenshot) {
        log(LogLevels.UNDEFINED, info, screenshot);
    }
}
