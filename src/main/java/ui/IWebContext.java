package ui;

import com.microsoft.playwright.Page;
import helpers.FileSystemHelper;
import management.playwright.run_management.Sessions;
import ui.containers.BaseElementContainer;
import ui.pages.BasePage;

import java.io.File;
import java.time.Duration;

public interface IWebContext {

    boolean waitForOpening();

    boolean waitForOpening(Duration timeout);

    void refresh();

    String getName();

    Page getPwPage();

    BasePage getPage();

    BaseElementContainer getContainer();

    default void STEP(String info) {
        STEP(info, true);
    }

    default void STEP(String info, boolean withScreenshot) {
        if(withScreenshot)
            Sessions.getCurrentSession().getLoggerSession().STEP(info, makeDefaultScreenshot());
        else
            Sessions.getCurrentSession().getLoggerSession().STEP(info);
    }

    default void FATAL(String info) {
        FATAL(info, true);
    }

    default void FATAL(String info, boolean withScreenshot) {
        if(withScreenshot)
            Sessions.getCurrentSession().getLoggerSession().FATAL(info, makeDefaultScreenshot());
        else
            Sessions.getCurrentSession().getLoggerSession().FATAL(info);
    }

    default File makeScreenshot(boolean isFullPage){
        byte[] buffer = getPwPage().screenshot(new Page.ScreenshotOptions().setFullPage(isFullPage));
        return FileSystemHelper.createScreenshotFile(buffer);
    }

    default File makeScreenshotIfPossible(boolean isFullPage){
        byte[] buffer = getPwPage().screenshot(new Page.ScreenshotOptions().setFullPage(isFullPage));
        return FileSystemHelper.createScreenshotFile(buffer);
    }

    default File makeDefaultScreenshot(){
        return makeScreenshot(false);
    }
}
