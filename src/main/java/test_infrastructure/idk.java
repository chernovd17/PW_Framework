package test_infrastructure;

import LOGGER.withlog4j2.TestLogger;
import lombok.Getter;
import lombok.Setter;
import management.playwright.BrowserManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class idk {

    private BrowserManager browserManager;
    private TestLogger testLogger;
    private Thread thread;

    public idk(TestLogger testLogger) {
        this.testLogger = testLogger;
        thread = Thread.currentThread();
    }

}
