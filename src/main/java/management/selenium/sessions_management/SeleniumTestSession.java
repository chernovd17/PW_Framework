package management.selenium.sessions_management;

import logger_and_report.withlog4j2.TestLogger;
import lombok.Getter;

import java.util.Locale;

public class SeleniumTestSession{

    @Getter
    private SeleniumBrowserManager browserManager;
    @Getter
    private TestLogger loggerSession;
    private long threadId;

    public SeleniumTestSession(SeleniumBrowserManager browserManager, TestLogger loggerSession, long threadId) {
        this.browserManager = browserManager;
        this.loggerSession = loggerSession;
        this.threadId = threadId;
        Locale.setDefault(Locale.ENGLISH);
    }

    public long getThreadId() {
        return threadId;
    }
}
