package helpers;

import management.playwright.run_management.Sessions;
import org.testng.Assert;

public class Validation {

    public static void assertTrue(boolean condition, String message) {
        String msg = "ASSERT: " + message;
        truthValidation(condition, msg, false);
    }

    public static void verifyTrue(boolean condition, String message) {
        String msg = "VERIFY: " + message;
        truthValidation(condition, msg, false);
    }

    private static void truthValidation(boolean condition, String message, boolean isFatal) {
        try {
            Assert.assertTrue(condition);
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message);
        } catch (AssertionError e) {
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(message);
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(message);
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        String msg = "ASSERT: " + message + "\nActual value: " + actual + "\nExpected value: " + expected;
        equalsValidation(actual, expected, msg, true);
    }

    public static void verifyEquals(Object actual, Object expected, String message) {
        String msg = "VERIFY: " + message + "\nActual value: " + actual + "\nExpected value: " + expected;
        equalsValidation(actual, expected, msg, false);
    }

    private static void equalsValidation(Object actual, Object expected, String message, boolean isFatal) {
        try {
            Assert.assertEquals(actual, expected, message);
            Sessions.getCurrentSession().getLoggerSession().SUCCESS(message);
        } catch (AssertionError e) {
            if(isFatal)
                Sessions.getCurrentSession().getLoggerSession().FATAL(message);
            else
                Sessions.getCurrentSession().getLoggerSession().FAIL(message);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
}


