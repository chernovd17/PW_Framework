package LOGGER.entities;

import lombok.Getter;
import lombok.Setter;
import org.testng.ITestContext;

import java.time.LocalDateTime;

@Setter
@Getter
public class SuiteInfo {

    private String title;

    private int allTestsCount;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private ITestContext context;

    public SuiteInfo(ITestContext context){
        this.context = context;
        title = context.getName();
        startDateTime = LocalDateTime.now();
    }

}
