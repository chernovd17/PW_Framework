package example;

import LOGGER.withlog4j2.GlobalLoggerSession;
import base_tests.BaseTest;
import com.microsoft.playwright.BrowserType;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BaseExampleTest extends BaseTest {

    /*@BeforeMethod
    protected void launch(ITestContext context, Method method, Object[] params){
        GlobalLoggerSession.getSession().resetTestLoggerSession();
        Test annotation = method.getAnnotation(Test.class);
        GlobalLoggerSession.getSession().initTestLoggerSession(annotation);
        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel(ExampleEnvironment.get().getBrowserName())
                .setArgs(args);
        browserManager = BrowserManager.createNew(ExampleEnvironment.get().getBrowserName(), type);
        browserManager.navigate(ExampleEnvironment.get().getAppUrl());
        getLogger().SYSTEM("Start Test: '" + method.getName() + "'");

    }*/

}
