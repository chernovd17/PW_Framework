package logger_and_report.entities;

import helpers.FileSystemHelper;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.LogEvent;
import org.testng.ITestContext;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SuiteInfo {

    private String title;

    private int allTestsCount;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int countOfPassedTests = 0;
    private int countOfFailedTests = 0;
    private int countOfFatalTests = 0;
    private int countOfSkippedTests = 0;
    private int countOfUnknownTests = 0;
    private final List<TestInfo> tests = new ArrayList<>();
    private ITestContext context;
    
    private static SuiteInfo instance;

    private List<ReportRow> reportRows = new ArrayList<>();


    private SuiteInfo(ITestContext context){
        this.context = context;
        title = context.getName();
        startDateTime = LocalDateTime.now();
        allTestsCount = context.getAllTestMethods().length;
    }

    public static SuiteInfo initSuite(ITestContext context) {
        if (instance == null) {
            instance = new SuiteInfo(context);
        }
        return instance;
    }
    
    public static SuiteInfo getInstance() {
        if(instance == null)
            throw new IllegalStateException("SuiteInfo is not initialized");
        return instance;
    }

    public synchronized void addSuccess() {
        countOfPassedTests++;
    }

    public synchronized void addFailure() {
        countOfFailedTests++;
    }

    public synchronized void addFatal() {
        countOfFatalTests++;
    }

    public synchronized void addSkipped() {
        countOfSkippedTests++;
    }

    public synchronized void addUnknown() {
        countOfSkippedTests++;
    }

    public void addTestInfo(TestInfo testInfo){
        switch (testInfo.getTestStatus()) {
            case SUCCESS -> addSuccess();
            case FATAL -> addFatal();
            case FAIL -> addFailure();
            case UNKNOWN -> addUnknown();
            default -> addSkipped();
        }
        synchronized (tests){
            tests.add(testInfo);
        }
    }

    public void print() {
        endDateTime = LocalDateTime.now();
        System.out.println(this);
    }

    public String toString(){
        return "Total Tests: " + allTestsCount +
                "\nSuccess: " + countOfPassedTests +
                "\nFailed: " + countOfFailedTests +
                "\nFatal: " + countOfFatalTests +
                "\nSkipped: " + countOfSkippedTests +
                "\nDuration: " + calculateDuration();
    }

    private String calculateDuration(){
        endDateTime = LocalDateTime.now();
        Duration duration = Duration.between(startDateTime, endDateTime);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
    }

    public void addRow(LogEvent event) {
        Object[] parameters = event.getMessage().getParameters();
        File screenshot = null;
        if (parameters != null && parameters.length > 0 && parameters[0] instanceof byte[] screenshotData) {
            screenshot = FileSystemHelper.createScreenshotFile(screenshotData);
        }
        reportRows.add(ReportRow.createReportRow(event, screenshot));
    }
}
