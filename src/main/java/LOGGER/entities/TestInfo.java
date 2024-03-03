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

    public static String FAILED_TEST_PATTER = "Test is finished with %s UNCRITICAL errors:\n";
    private String title;
    private String description;
    private String comment;
    private String bug;
    private String id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    private List<ReportRow> reportRows = new ArrayList<>();

    private Test testAnnotation;

    public TestInfo(Test testAnnotation){
        this.testAnnotation = testAnnotation;
        title = testAnnotation.testName();
        description = testAnnotation.description();
        startDateTime = LocalDateTime.now();
    }

    public void addRow(ReportRow row){
        reportRows.add(row);
    }

    public String getTestStatus(){

        List<ReportRow> rows = getReportRows();

        List<ReportRow> failedRows = rows.stream().filter(
                row -> row.getLogLevels().equals(LogLevels.FAIL)).toList();

        List<ReportRow> fatalRows = rows.stream().filter(
                row -> row.getLogLevels().equals(LogLevels.FATAL)).toList();

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



}
