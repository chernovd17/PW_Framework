package management.selenium.sessions_management;

import logger_and_report.withlog4j2.TestLogger;
import management.environment.DefaultEnvironment;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.*;

public class SeleniumSessions {

    private final static List<SeleniumTestSession> sessions = new ArrayList<>();
    public final static Set<String> uniqueSessionsIds = new HashSet<>();

    public static synchronized void createSession(Test annotation){
        SeleniumBrowserManager localBrowserManager = SeleniumBrowserManager.createNew(DefaultEnvironment.get().getBrowserName());
        TestLogger logger = new TestLogger(annotation);
        SeleniumTestSession session = new SeleniumTestSession(localBrowserManager, logger, Thread.currentThread().threadId());
        sessions.add(session);
        uniqueSessionsIds.add(String.valueOf(session.getThreadId()));
    }

    private static synchronized void createSeleniumSession(Test annotation){
        SeleniumBrowserManager localBrowserManager = SeleniumBrowserManager.createNew(DefaultEnvironment.get().getBrowserName());
        TestLogger logger = new TestLogger(annotation);
        SeleniumTestSession session = new SeleniumTestSession(localBrowserManager, logger, Thread.currentThread().threadId());
        sessions.add(session);
        uniqueSessionsIds.add(String.valueOf(session.getThreadId()));
    }


    public static synchronized SeleniumTestSession getCurrentSession() {

        long currentThreadId = Thread.currentThread().threadId();
        synchronized (sessions) {
            Optional<SeleniumTestSession> matchingSession = sessions.stream()
                    .filter(session -> session.getThreadId() == currentThreadId)
                    .findFirst();
            return matchingSession.orElse(null);
        }
    }

    public SeleniumBrowserManager getBrowserSession() {
        return getCurrentSession().getBrowserManager();
    }

    public synchronized static void killCurrentSession() {
        getCurrentSession().getLoggerSession().SYSTEM("Killing current session '" + getCurrentSession().getThreadId() + "'.");
        getCurrentSession().getBrowserManager().closeBrowser();
        getCurrentSession().getBrowserManager().closeDriver();
        getCurrentSession().getLoggerSession().getTestInfo().setEndDateTime(LocalDateTime.now());

        sessions.remove(getCurrentSession());
    }

    public static void killAllSessions() {
        for (SeleniumTestSession session : sessions) {
            session.getLoggerSession().SYSTEM("Killing Session '" + getCurrentSession().getThreadId() + "'.");
            session.getBrowserManager().closeBrowser();
            session.getBrowserManager().closeDriver();
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
