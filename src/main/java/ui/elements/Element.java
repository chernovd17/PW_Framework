package ui.elements;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.BoundingBox;
import helpers.FileSystemHelper;
import management.environment.DefaultEnvironment;
import management.playwright.run_management.Sessions;
import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.io.File;
import java.time.Duration;
import java.util.function.BooleanSupplier;

public class Element {

    public static final Duration defaultElementDuration = DefaultEnvironment.get().getElementTimeout();
    private String name;
    private BasePage page;
    private BaseElementContainer parent;
    private IWebContext context;
    private ElementHandle handle;
    private Loc loc;
    private Locator pwLoc;

    public Element(String name, IWebContext context, Loc loc) {
        this(name, context, loc, false);
    }

    /** ElementHandle works faster than toLocator() if we use some actions in a row with 1 element,
     *  .toLocator() finds element for each action*/
    public Element(String name, IWebContext context, Loc loc, boolean handleInitialization) {
        this.name = name;
        this.context = context;
        this.page = context.getPage();
        this.parent = context.getContainer();
        this.loc = loc;
        this.pwLoc = getPWLoc();
        if(handleInitialization)
            initializeElementHandle();
    }

    private void initializeElementHandle(){
        if(parent == null)
            this.handle = page.getPwPage().querySelector(loc.toString());
        else
            this.handle = parent.getContainerAsElement().getHandle().querySelector(loc.toString());
    }

    public void click() {
        ACTION(String.format("Click '%s'", name));
        try {
            //todo need to clarify how waitings work in playwright
            // now it looks that defaultPageTimeout we can ignore only like below,
            // but we can't set PageTimeout as 1 millis and wait element more than 1 millis
            getPWLoc().click(new Locator.ClickOptions().setForce(true).setTimeout(1));
        } catch (Exception e){
            logError(e.getMessage());
        }
    }

    public void waitAndClick(Duration durationInSec) {
        ACTION(String.format("Click '%s'", name));
        try {
            getPWLoc().click(new Locator.ClickOptions().setTimeout(durationInSec.toMillis()));
        } catch (Exception e){
            logError(e.getMessage());
        }
    }

    public void typeText(String text) {
        ACTION(String.format("Type Text '%s' in element '%s'", text, name));
        getPWLoc().type(text);
    }

    public void fillText(String text) {
        ACTION(String.format("Fill Text '%s' in element '%s'", text, name));
        try {
            getPWLoc().fill(text);
        } catch (Exception e){
            logError(e.getMessage());
        }
    }

    private void logError(String message){
        FATAL(String.format("FATAL error occurred with element '%s':\n%s", name, message), getPage().makeScreenshot(false));
    }

    public void makeScreenScreenshot(){
        getPage().makeScreenshot(false);
    }

    public File makeScreenshot(){
        byte[] buffer = getPWLoc().screenshot();
        return FileSystemHelper.createScreenshotFile(buffer);
    }

    public void fillTextWithForce(String text) {
        ACTION(String.format("Fill Text '%s' in element '%s'", text, name));
        try {
            getPWLoc().fill(text, new Locator.FillOptions().setForce(true));
        } catch (Exception e){
            logError(e.getMessage());
        }
    }

    public String getText() {
        ACTION("Get text from Element '" + getLogicalName() + "'.");
        try {
            return getPWLoc().innerText();
        } catch (Exception e){
            logError(e.getMessage());
        }
        return null;
    }

    public String getProperty(String property) {
        try {
            return getPWLoc().getAttribute(property);
        } catch (Exception e){
            logError(e.getMessage());
        }
        return null;
    }

    public boolean visible() {
        try {
            return getPWLoc().isVisible();
        } catch (Exception e){
            logError(e.getMessage());
        }
        return false;
    }

    public boolean notVisible() {
        return !visible();//by tests isVisible faster than isHidden
    }

    public boolean waitForVisible(Duration timeout) {
        //page.getPwPage().waitForSelector(loc.toString());
        try {
            return waitForCondition(() -> getPWLoc().isVisible(), timeout);
        } catch (Exception e){
            logError(e.getMessage());
        }
        return false;
    }

    public boolean waitForHidden(Duration timeout) {
        try {
            return waitForCondition(() -> getPWLoc().isHidden(), timeout);
        } catch (Exception e){
            logError(e.getMessage());
        }
        return false;
    }

    public boolean waitForHidden() {
        return waitForHidden(defaultElementDuration);
    }

    public boolean waitForVisible() {
        return waitForVisible(defaultElementDuration);
    }

    public ElementHandle getHandle() {
        return handle;
    }

    protected Locator getPWLoc() {
        if(parent != null)
            return parent.getContainerAsElement().getPWLoc().locator(loc.toString()).first();
        else
            return page.getPwPage().locator(loc.toString()).first();//first() -- to ignore "Error: strict mode violation" when locator return more than 1 elements
    }

    protected final boolean waitForCondition(BooleanSupplier condition, Duration timeout) {
        if (timeout.toMillis() == 0) {
            return condition.getAsBoolean();
        }
        try {
            page.getPwPage().waitForCondition(condition, new Page.WaitForConditionOptions().setTimeout(timeout.toMillis()));
            return true;
        } catch (TimeoutError ignore) {
            return false;
        }
    }

    public String getLogicalName() {
        return name;
    }

    public BoundingBox getBoundingBox(){
        return getPWLoc().boundingBox();
    }

    public BasePage getPage(){
        return page;
    }

    public void pressKeyboardKey(String key){
        getPage().getPwPage().keyboard().press(key);
    }

    public boolean waitForText(String text) {
        return waitForCondition(() -> getPWLoc().innerText().equals(text), defaultElementDuration);
    }

    public boolean waitForContainsText(String text) {
        return waitForCondition(() -> getPWLoc().innerText().contains(text), defaultElementDuration);
    }

    protected void ACTION(String info) {
        Sessions.getCurrentSession().getLoggerSession().ACTION(info);
    }

    protected void FATAL(String info) {
        Sessions.getCurrentSession().getLoggerSession().FATAL(info);
    }

    protected void ACTION(String info, File screenshot) {
        Sessions.getCurrentSession().getLoggerSession().ACTION(info, screenshot);
    }

    protected void FATAL(String info, File screenshot) {
        Sessions.getCurrentSession().getLoggerSession().FATAL(info, screenshot);
    }
}