package helpers;

import management.playwright.run_management.Sessions;
import org.testng.Assert;

import java.io.File;

public class Validation {

    public synchronized static void assertTrue(boolean condition, String message) {
        String msg = "ASSERT: " + message;
        truthValidation(condition, msg, false);
    }

    public synchronized static void verifyTrue(boolean condition, String message) {
        String msg = "VERIFY: " + message;
        truthValidation(condition, msg, false);
    }

    private synchronized static void truthValidation(boolean condition, String message, boolean isFatal) {
        try {
            Assert.assertTrue(condition);
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(message, makeScreenshot());
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(message, makeScreenshot());
        }
    }

    public synchronized static void assertEquals(Object actual, Object expected, String message) {
        String msg = "ASSERT: " + message + "\nActual value: " + actual + "\nExpected value: " + expected;
        equalsValidation(actual, expected, msg, true);
    }

    public synchronized static void verifyEquals(Object actual, Object expected, String message) {
        String msg = "VERIFY: " + message + "\nActual value: " + actual + "\nExpected value: " + expected;
        equalsValidation(actual, expected, msg, false);
    }

    private synchronized static void equalsValidation(Object actual, Object expected, String message, boolean isFatal) {
        try {
            Assert.assertEquals(actual, expected, message);
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(message, makeScreenshot());
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(message, makeScreenshot());
        }
    }

    private synchronized static File makeScreenshot() {
        return Sessions.getCurrentSession().getBrowserManager().makeScreenshot();
    }
}


