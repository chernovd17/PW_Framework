package ui.elements;

import LOGGER.GlobalLoggerSession;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.BoundingBox;
import lombok.extern.log4j.Log4j2;
import management.environment.DefaultEnvironment;
import org.apache.logging.log4j.Logger;
import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.time.Duration;
import java.util.function.BooleanSupplier;

@Log4j2
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
        getLogger().info(String.format("Click '%s'", name));
        //DefaultHelper.sleep(Duration.ofSeconds(2));
        getPWLoc().click();
    }

    public void typeText(String text) {
        getLogger().info(String.format("Type Text '%s' in element '%s'", text, name));
        //DefaultHelper.sleep(Duration.ofSeconds(2));//
        getPWLoc().type(text);
    }

    public void fillText(String text) {
        //getLogger().info(String.format("Fill Text '%s' in element '%s'", text, name));
        ACTION(String.format("Fill Text '%s' in element '%s'", text, name));

        getPWLoc().fill(text);
    }

    public void fillTextWithForce(String text) {
        getLogger().info(String.format("Fill Text '%s' in element '%s'", text, name));
        getPWLoc().fill(text, new Locator.FillOptions().setForce(true));
    }

    public String getText() {
        ACTION("Get text from Element '" + getLogicalName() + "'.");
        return getPWLoc().innerText();
    }

    public String getProperty(String property) {
        return getPWLoc().getAttribute(property);
    }

    public boolean visible() {
        return getPWLoc().isVisible();
    }

    public boolean notVisible() {
        return !visible();//by tests isVisible faster than isHidden
    }

    public boolean waitForVisible(Duration timeout) {
        //page.getPwPage().waitForSelector(loc.toString());
        return waitForCondition(() -> getPWLoc().isVisible(), timeout);
    }

    public boolean waitForHidden(Duration timeout) {
        return waitForCondition(() -> getPWLoc().isHidden(), timeout);
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

    protected Logger getLogger() {
        return log;
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
        log.info("ELEMENT: " + info);
        GlobalLoggerSession.getSession().getTestLogger().action(info);
    }
}