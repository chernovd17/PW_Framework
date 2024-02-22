package ui;

import com.microsoft.playwright.Page;
import lombok.extern.log4j.Log4j2;
import ui.containers.BaseElementContainer;
import ui.pages.BasePage;

import java.time.Duration;

public interface IWebContext {

    boolean waitForOpening();

    boolean waitForOpening(Duration timeout);

    void refresh();

    String getName();

    Page getPwPage();

    BasePage getPage();

    BaseElementContainer getContainer();
}
