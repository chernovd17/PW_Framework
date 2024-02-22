package example;

import base_tests.BaseTest;
import com.microsoft.playwright.BrowserType;
import lombok.extern.log4j.Log4j2;
import management.environment.example.ExampleEnvironment;
import management.playwright.BrowserManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;

public class BaseExampleTest extends BaseTest {

    @BeforeMethod
    protected void launch(){
        getLogger().info("Start");
        List<String> args = new ArrayList<>();
        args.add("--start-maximized");
        BrowserType.LaunchOptions type = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setChannel(ExampleEnvironment.get().getBrowserName())
                .setArgs(args);
        browserManager = BrowserManager.createNew(ExampleEnvironment.get().getBrowserName(), type);
        browserManager.navigate(ExampleEnvironment.get().getAppUrl());
    }

}
