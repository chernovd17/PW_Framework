package management.playwright.run_management;

import LOGGER.withlog4j2.TestLogger;
import com.microsoft.playwright.BrowserType;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sessions {

    private final static List<TestSession> sessions = new ArrayList<>();

    public static synchronized void createSession(Test annotation){

        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel("chrome")
                .setArgs(args);
        BrowserManager localBrowserManager = BrowserManager.createNew(ExampleEnvironment.get().getBrowserName(), type);
        TestLogger logger = new TestLogger(annotation);
        TestSession session = new TestSession(localBrowserManager, logger, Thread.currentThread().threadId());
        sessions.add(session);
    }

    public static synchronized TestSession getCurrentSession() {

        long currentThreadId = Thread.currentThread().threadId();
        synchronized (sessions) {
            Optional<TestSession> matchingSession = sessions.stream()
                    .filter(session -> session.getThreadId() == currentThreadId)
                    .findFirst();
            return matchingSession.orElse(null);
        }
    }

    public BrowserManager getBrowserSession() {
        return getCurrentSession().getBrowserManager();
    }

    public synchronized static void killCurrentSession() {
        getCurrentSession().getLoggerSession().SYSTEM("Killing current session '" + getCurrentSession().getThreadId() + "'.");
        getCurrentSession().getBrowserManager().closeBrowser();
        getCurrentSession().getLoggerSession().getTestInfo().setEndDateTime(LocalDateTime.now());
        sessions.remove(getCurrentSession());
    }

    public static void killAllSessions() {
        for (TestSession session : sessions) {
            session.getLoggerSession().SYSTEM("Killing session '" + getCurrentSession().getThreadId() + "'.");
            session.getBrowserManager().closeBrowser();
            session.getLoggerSession().getTestInfo().setEndDateTime(LocalDateTime.now());
        }
        sessions.clear();
    }

    private static boolean sessionsExist() {
        return !sessions.isEmpty();
    }
}
