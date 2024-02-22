package LOGGER.info;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReportRow {

    private LocalDateTime dateTime;
    private InfoRowType infoType;
    private String info;
    private String screenshot;

    private ReportRow(InfoRowType infoType, String info, String screenshot){
        this.dateTime = LocalDateTime.now();
        this.infoType = infoType;
        this.info = info;
        this.screenshot = screenshot;
    }

    public static ReportRow createStepReportRow(String info, String screenshot){
        return new ReportRow(InfoRowType.STEP, info, screenshot);
    }

    public static ReportRow createActionReportRow(String info, String screenshot){
        return new ReportRow(InfoRowType.ACTION, info, screenshot);
    }

    public static ReportRow createSuccessReportRow(String info, String screenshot){
        return new ReportRow(InfoRowType.SUCCESS, info, screenshot);
    }

    public static ReportRow createFailReportRow(String info, String screenshot){
        return new ReportRow(InfoRowType.FAIL, info, screenshot);
    }

    public static ReportRow createErrorReportRow(String info, String screenshot){
        return new ReportRow(InfoRowType.ERROR, info, screenshot);
    }
}
