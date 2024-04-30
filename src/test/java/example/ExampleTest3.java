package example;

import base_tests.BaseTest;
import helpers.Validation;
import org.testng.annotations.Test;


public class ExampleTest3 extends BaseTest {

    @Test(testName = "TestEx3", description = "descr3")
    public void test3() throws InterruptedException {

        Validation.verifyTrue(true, "Verify if Test3 exists");
        Validation.assertEquals(true,  true,"Verify Page Title");
        Validation.assertTrue(true, "Verify correct version");

        generateTestFinalStatus();
    }
}
