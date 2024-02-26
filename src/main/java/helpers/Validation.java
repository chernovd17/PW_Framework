package helpers;

import LOGGER.withlog4j2.GlobalLoggerSession;
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
            GlobalLoggerSession.getSession().SUCCESS(message);
        } catch (AssertionError e) {
            if(isFatal)
                GlobalLoggerSession.getSession().FATAL(message);
            else
                GlobalLoggerSession.getSession().FAIL(message);
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
            GlobalLoggerSession.getSession().SUCCESS(message);
        } catch (AssertionError e) {
            if(isFatal)
                GlobalLoggerSession.getSession().FATAL(message);
            else
                GlobalLoggerSession.getSession().FAIL(message);
        }
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
}


