package example;

import base_tests.BaseTest;
import helpers.Validation;
import org.testng.annotations.Test;

public class ExampleTest4 extends BaseTest {

    @Test(testName = "test4", description = "descr4")
    public void test4() {
        Validation.verifyTrue(Integer.parseInt("1") == 1, "Verify if \"1\" is 1");
        generateTestFinalStatus();
    }
}
