package ui;

import com.microsoft.playwright.Page;
import ui.blocks.BaseBlock;
import ui.pages.BasePage;

import java.time.Duration;

public interface IWebContext {

    boolean waitForOpening();

    boolean waitForOpening(Duration timeout);

    void refresh();

    String getName();

    Page getPwPage();

    BasePage getPage();

    BaseBlock getComponent();
}
