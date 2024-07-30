package management.selenium.web_context;

import helpers.FileSystemHelper;
import management.playwright.run_management.Sessions;
import management.selenium.sessions_management.SeleniumSessions;
import management.selenium.web_context.containers.BaseElementContainer;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.Duration;

public interface IWebContext {

    boolean waitForOpening();

    boolean waitForOpening(Duration timeout);

    void refresh();

    String getName();

    WebDriver getDriver();

    BasePage getPage();

    BaseElementContainer getContainer();

    default void STEP(String info) {
        STEP(info, true);
    }

    default void STEP(String info, boolean withScreenshot) {
        if(withScreenshot)
            SeleniumSessions.getCurrentSession().getLoggerSession().STEP(info, makeDefaultScreenshot());
        else
            SeleniumSessions.getCurrentSession().getLoggerSession().STEP(info);
    }

    default void FATAL(String info) {
        FATAL(info, true);
    }

    default void FATAL(String info, boolean withScreenshot) {
        if(withScreenshot)
            SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(info, makeDefaultScreenshot());
        else
            SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(info);
    }

    default void FAIL(String info, boolean withScreenshot) {
        if(withScreenshot)
            SeleniumSessions.getCurrentSession().getLoggerSession().FAIL(info, makeDefaultScreenshot());
        else
            SeleniumSessions.getCurrentSession().getLoggerSession().FAIL(info);
    }

    default void FAIL(String info) {
        FAIL(info, true);
    }


    default File makeDefaultScreenshot(){
        return FileSystemHelper.createSeleniumScreenshotFile(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
    }
}
