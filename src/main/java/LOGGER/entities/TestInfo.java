package LOGGER.entities;

import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

}
