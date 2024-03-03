package LOGGER.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;

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

    private List<TestInfo> tests = new ArrayList<>();

    private ITestContext context;

    public SuiteInfo(ITestContext context){
        this.context = context;
        title = context.getName();
        startDateTime = LocalDateTime.now();
        allTestsCount = context.getAllTestMethods().length;
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

    public void addTestInfo(TestInfo testInfo){
        String status = testInfo.getTestStatus();
        switch (status) {
            case "PASSED" -> addSuccess();
            case "FATAL" -> addFatal();
            case "FAILED" -> addFailure();
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
}
