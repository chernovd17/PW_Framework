package management.selenium.helpers;

import management.selenium.sessions_management.SeleniumSessions;
import org.testng.Assert;

import java.io.File;

public class Verification {

    public static void assertTrue(boolean condition, String message) {
        String msg = "ASSERT: " + message;
        truthValidation(condition, msg, true);
    }

    public static void verifyTrue(boolean condition, String message) {
        String msg = "VERIFY: " + message;
        truthValidation(condition, msg, false);
    }

    private static void truthValidation(boolean condition, String message, boolean isFatal) {
        try {
            Assert.assertTrue(condition);
            SeleniumSessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            if(isFatal)
                SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(message, makeScreenshot());
            else
                SeleniumSessions.getCurrentSession().getLoggerSession().FAIL(message, makeScreenshot());
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        String msg = String.format("ASSERT: %s\nActual: %s\nExpected: %s",message, actual, expected);
        equalsValidation(actual, expected, msg, true);
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        String msg = String.format("VERIFY: %s\nActual: %s\nExpected: %s",message, actual, expected);
        equalsValidation(actual, expected, msg, false);
    }

    private static void equalsValidation(Object actual, Object expected, String message, boolean isFatal) {
        try {
            Assert.assertEquals(actual, expected, message);
            SeleniumSessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            if(isFatal)
                SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(message, makeScreenshot());
            else
                SeleniumSessions.getCurrentSession().getLoggerSession().FAIL(message, makeScreenshot());
        }
    }

    private static File makeScreenshot() {
        return SeleniumSessions.getCurrentSession().getBrowserManager().makeScreenshot();
    }
}


