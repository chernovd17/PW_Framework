package logger_and_report.withlog4j2;

import logger_and_report.entities.LogLevels;
import logger_and_report.entities.ReportRow;
import logger_and_report.entities.TestInfo;
import helpers.FileSystemHelper;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.apache.logging.log4j.core.LogEvent;

import java.io.File;

public class TestLogger {

    private static final String PASSED_TEST_PATTERN = "Test is finished SUCCESSFULLY";
    private static final String FAILED_TEST_PATTERN = "Test is finished with %s UNCRITICAL errors:\n%s";
    private static final String FATAL_TEST_PATTERN = "Test is finished with %s FATAL error:\n%s\n And contains %s UNCRITICAL errors:\n%s";
    private static final String NO_VALIDATIONS_IN_TEST = "Test doesn't contains any validation steps";
    private static final String TEST_WAS_SKIPPED = "Test was skipped";

    @Getter
    private TestInfo testInfo;
    private static Logger logger;

    public TestLogger(Test annotation){
        testInfo = new TestInfo(annotation);
        logger = LogManager.getLogger(CustomUIAppender.class);
    }


    public void addRow(LogEvent event) {
        Object[] parameters = event.getMessage().getParameters();
        File screenshot = null;
        if (parameters != null && parameters.length > 0 && parameters[0] instanceof byte[] screenshotData) {
            screenshot = FileSystemHelper.createScreenshotFile(screenshotData);
        }
        testInfo.addRow(ReportRow.createReportRow(event, screenshot));
    }

    private void log(LogLevels level, String info, File screenshot){
        /*logger.*/log(level.getLevel(), info, screenshot);
    }

    public void log(Level level, String info, File screenshot){
        logger.log(level, info, screenshot);
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
        throw new Error(info);
    }

    public void FATAL(String info) {
        log(LogLevels.FATAL, info);
        throw new Error(info);
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

    //use it only for last step in testMethod or as first row in afterMethod
    public void addTestFinalStatusToLogInLastStep(File screenshot){
        addTestFinalStatusToLog(screenshot, true);
    }

    public void addTestFinalStatusToLog(File screenshot){
        addTestFinalStatusToLog(screenshot, false);
    }

    private void addTestFinalStatusToLog(File screenshot, boolean isLastStep) {
        testInfo.setCaseFullyCompleted();
        if (testInfo.testHasFatalRows()) {
            String resultInfo = String.format(FATAL_TEST_PATTERN, testInfo.getCountOfFatalRows(), testInfo.getRowsAsString(LogLevels.FATAL),
                    testInfo.getCountOfFailedRows(), testInfo.getRowsAsString(LogLevels.FAIL));
            if(isLastStep)
                FATAL(resultInfo, screenshot);
            else
                FAIL(resultInfo, screenshot);
            testInfo.setFatalTestStatus();
        }
        else if (testInfo.testHasFailedRows()) {
            String failInfo = String.format(FAILED_TEST_PATTERN, testInfo.getCountOfFailedRows(), testInfo.getRowsAsString(LogLevels.FAIL));
            FAIL(failInfo, screenshot);
            testInfo.setFailTestStatus();
            throw new AssertionError(failInfo);
        }
        else if (testInfo.testHasSuccessRows()) {
            SUCCESS(PASSED_TEST_PATTERN, screenshot);
            testInfo.setSuccessTestStatus();
        }
        else if (!testInfo.getTestRows().isEmpty()) {
            if(isLastStep)
                FATAL(NO_VALIDATIONS_IN_TEST, screenshot);
            else
                FAIL(NO_VALIDATIONS_IN_TEST, screenshot);
            testInfo.setFatalTestStatus();
        }
        else {
            SYSTEM(TEST_WAS_SKIPPED);
            testInfo.setSkippedTestStatus();
        }
    }

}
