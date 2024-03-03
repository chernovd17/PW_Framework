package management.playwright.run_management;

import LOGGER.withlog4j2.TestLogger;
import lombok.Getter;
import management.playwright.BrowserManager;

public class TestSession {

    @Getter
    private BrowserManager browserManager;
    @Getter
    private TestLogger loggerSession;
    private long threadId;

    public TestSession(BrowserManager browserManager, TestLogger loggerSession, long threadId) {
        this.browserManager = browserManager;
        this.loggerSession = loggerSession;
        this.threadId = threadId;
    }

    public long getThreadId() {
        return threadId;
    }
}
