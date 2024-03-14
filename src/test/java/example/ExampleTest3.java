package example;

import base_tests.BaseTest;
import helpers.Validation;
import org.testng.annotations.Test;


public class ExampleTest3 extends BaseTest {

    @Test(testName = "TestEx3", description = "descr3")
    public void test() throws InterruptedException {

        Validation.verifyTrue(true, "Test3 vtrue");
        Validation.verifyTrue(true, "Test3 vfalse");

        Validation.assertEquals(true,  true,"Test3 atrue");
        Validation.assertTrue(true, "Test3 afalse");

        generateTestFinalStatus();
    }
}
