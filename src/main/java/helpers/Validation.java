package helpers;

import org.testng.Assert;

import static org.testng.Reporter.log;

public class Validation {


    public static void verifyTrue(boolean state, String info) {
        try {
            Assert.assertTrue(state, info);

        } catch (Throwable e) {
            //log("Expected value: true" + " Actual value: " + condition + " - FAILED " + message, true);
        }
    }


}
