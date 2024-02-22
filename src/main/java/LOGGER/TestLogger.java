package LOGGER;

import LOGGER.info.ReportRow;
import LOGGER.info.TestInfo;
import org.testng.annotations.Test;

public class TestLogger {

    private TestInfo testInfo;

    public TestLogger(Test annotation){
        testInfo = new TestInfo(annotation);
    }

    public void step(String info) {
        testInfo.addRow(ReportRow.createStepReportRow(info, ""));//todo add screenshot
    }

    public void action(String info) {
        testInfo.addRow(ReportRow.createActionReportRow(info, ""));
    }
}
