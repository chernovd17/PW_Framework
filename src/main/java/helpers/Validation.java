package helpers;

import management.playwright.run_management.Sessions;
import org.testng.Assert;

import java.io.File;

public class Validation {

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
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(message, makeScreenshot());
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(message, makeScreenshot());
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        String msg = "ASSERT: " + message;
        equalsValidation(actual, expected, msg, true);
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        String msg = "VERIFY: " + message + "-- Actual: " + actual + "; Expected: " + expected;
        equalsValidation(actual, expected, msg, false);
    }

    private static void equalsValidation(Object actual, Object expected, String message, boolean isFatal) {
        try {
            Assert.assertEquals(actual, expected, message);
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message, makeScreenshot());
        } catch (AssertionError e) {
            String msg = "       Act. Value: " + actual + "; Exp. Value: " + expected;
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(msg, makeScreenshot());
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(msg, makeScreenshot());
        }
    }

    private static File makeScreenshot() {
        return Sessions.getCurrentSession().getBrowserManager().makeScreenshot();
    }
}


