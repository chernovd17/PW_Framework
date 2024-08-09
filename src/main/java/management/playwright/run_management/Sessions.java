package management.playwright.run_management;

import logger_and_report.withlog4j2.TestLogger;
import com.microsoft.playwright.BrowserType;
import management.environment.DefaultEnvironment;
import management.playwright.BrowserManager;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.*;

public class Sessions {

    private final static List<TestSession> sessions = new ArrayList<>();
    public final static Set<String> uniqueSessionsIds = new HashSet<>();

    public static synchronized void createSession(Test annotation){

        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel("chrome")
                .setArgs(args);
        BrowserManager localBrowserManager = BrowserManager.createNew(DefaultEnvironment.get().getBrowserName(), type);
        TestLogger logger = new TestLogger(annotation);
        TestSession session = new TestSession(localBrowserManager, logger, Thread.currentThread().threadId());
        sessions.add(session);
        uniqueSessionsIds.add(String.valueOf(session.getThreadId()));
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
        getCurrentSession().getBrowserManager().closeDriverOrPw();
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

    public static int getCountOfUniqueSessions() {
        return uniqueSessionsIds.size();
    }

    private static boolean sessionsExist() {
        return !sessions.isEmpty();
    }
}
