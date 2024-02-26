package LOGGER.withlog4j2;

import LOGGER.entities.ReportRow;
import LOGGER.entities.TestInfo;
import helpers.FileSystemHelper;
import lombok.Getter;
import org.testng.annotations.Test;
import org.apache.logging.log4j.core.LogEvent;

import java.io.File;

public class TestLogger {

    @Getter
    private TestInfo testInfo;

    public TestLogger(Test annotation){
        testInfo = new TestInfo(annotation);
    }


    public void addRow(LogEvent event) {
        Object[] parameters = event.getMessage().getParameters();
        File screenshot = null;
        if (parameters != null && parameters.length > 0 && parameters[0] instanceof byte[] screenshotData) {
            screenshot = FileSystemHelper.createScreenshotFile(screenshotData);
        }
        testInfo.addRow(ReportRow.createReportRow(event, screenshot));
    }
}
