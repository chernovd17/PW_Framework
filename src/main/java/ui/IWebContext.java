package ui;

import LOGGER.withlog4j2.GlobalLoggerSession;
import com.microsoft.playwright.Page;
import helpers.FileSystemHelper;
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
            GlobalLoggerSession.getSession().STEP(info, makeDefaultScreenshot());
        else
            GlobalLoggerSession.getSession().STEP(info);
    }

    default File makeScreenshot(boolean isFullPage){
        byte[] buffer = getPwPage().screenshot(new Page.ScreenshotOptions().setFullPage(isFullPage));
        return FileSystemHelper.createScreenshotFile(buffer);
    }

    default File makeDefaultScreenshot(){
        return makeScreenshot(false);
    }
}
