package LOGGER.entities;

import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class TestInfo {

    private String title;
    private String description;
    private String comment;
    private String bug;
    private String id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<ReportRow> reportRows = new ArrayList<>();
    private List<ReportRow> beforeTestRows = new ArrayList<>();
    private List<ReportRow> afterTestRows = new ArrayList<>();
    private List<ReportRow> activeRows;
    private boolean isCaseFullyCompleted = false;//true if test has reached the end of case
    private Test testAnnotation;

    public TestInfo(Test testAnnotation){
        this.testAnnotation = testAnnotation;
        title = testAnnotation.testName();
        description = testAnnotation.description();
        startDateTime = LocalDateTime.now();
        makeBeforeTestRowsActive();
    }

    private void makeListActive(List<ReportRow> rows) {
        if (rows == reportRows)
            activeRows = reportRows;
        else if (rows == beforeTestRows)
            activeRows = beforeTestRows;
        else if (rows == afterTestRows)
            activeRows = afterTestRows;
        else
            throw new IllegalArgumentException("Invalid list specified");
    }

    public void makeReportRowsActive(){
        makeListActive(reportRows);
    }

    public void makeAfterTestRowsActive(){
        makeListActive(afterTestRows);
    }

    public void makeBeforeTestRowsActive(){
        makeListActive(beforeTestRows);
    }

    public void addRow(ReportRow row){
        if (activeRows != null) {
            activeRows.add(row);
        } else {
            throw new IllegalStateException("No active list set");
        }
    }

    public String getTestStatus(){

        List<ReportRow> rows = getReportRows();

        List<ReportRow> failedRows = rows.stream().filter(
                row -> row.getLogLevel().equals(LogLevels.FAIL)).toList();

        List<ReportRow> fatalRows = rows.stream().filter(
                row -> row.getLogLevel().equals(LogLevels.FATAL)).toList();

        if(failedRows.isEmpty() && fatalRows.isEmpty()){
            return "PASSED";
        }

        if(fatalRows.isEmpty() && !failedRows.isEmpty()){
            return "FAILED";
        }

        return "FATAL";
    }

    public void setEndDateTime(){
        endDateTime = LocalDateTime.now();
    }


    public String getAllReportRows(){
        return reportRows.stream().map(ReportRow::toString).collect(Collectors.joining("\n"));
    }

    public String getRowsAsString(LogLevels level){
        return reportRows.stream()
                .filter(row -> row.getLogLevel().equals(level))
                .map(ReportRow::getInfo)
                .collect(Collectors.joining("\n"));
    }

    private List<ReportRow> getAllFatalRows(){
        return reportRows.stream().filter(row -> row.getLogLevel().equals(LogLevels.FATAL)).toList();
    }

    private List<ReportRow> getAllFailedRows(){
        return reportRows.stream().filter(row -> row.getLogLevel().equals(LogLevels.FAIL)).toList();
    }

    public int getCountOfFatalRows(){
        return getAllFatalRows().size();//never should be more than 1
    }

    public int getCountOfFailedRows(){
        return getAllFailedRows().size();
    }

    public boolean testHasFatalRows(){
        return !getAllFatalRows().isEmpty();
    }

    public boolean testHasFailedRows(){
        return !getAllFailedRows().isEmpty();
    }

    public boolean testHasSuccessRows(){
        return reportRows.stream().anyMatch(row -> row.getLogLevel().equals(LogLevels.SUCCESS));
    }

    public void setCaseFullyCompleted(){
        isCaseFullyCompleted = true;
    }

}
