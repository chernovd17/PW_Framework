package management.selenium.elements;

import helpers.Durations;
import management.selenium.web_context.containers.BaseElementContainer;
import management.selenium.web_context.IWebContext;
import management.selenium.sessions_management.SeleniumSessions;
import management.selenium.web_context.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;

public class Element {

    public static final Duration defaultElementDuration = Durations.FIVE_SECONDS;
    public static final Duration pollingDuration = Durations.HALF_SECOND;
    private String name;
    private BasePage page;
    private BaseElementContainer parent;
    private By locator;
    private IWebContext context;

    private WebElement webElement;

    public Element(String name, IWebContext context, By loc) {
        this.name = name;
        this.context = context;
        this.page = context.getPage();
        this.locator = loc;
        this.parent = context.getContainer();
    }

    public void click() {
        waitAndClick(defaultElementDuration);
    }

    public void waitAndClick(Duration durationInSec) {
        ACTION(String.format("Click '%s'", name));
        try {
            getWebElement().click();
        } catch (Exception e){
            logError(e.getMessage());
        }
    }

    public void setText(String text) {
        ACTION(String.format("Type Text '%s' in element '%s'", text, name));
        getWebElement().sendKeys(text);
    }

    protected void logError(String message){
        FATAL(String.format("FATAL error occurred with element '%s':\n%s", name, message), getPage().makeDefaultScreenshot());
    }

    public String getText() {
        ACTION("Get text from Element '" + getLogicalName() + "'.");
        try {
            return getWebElement().getText();
        } catch (Exception e){
            logError(e.getMessage());
        }
        return null;
    }

    public String getAttribute(String property) {
        try {
            return getWebElement().getAttribute(property);
        } catch (Exception e){
            logError(e.getMessage());
        }
        return null;
    }

    public boolean visible() {
        try {
            return getWebElement().isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean notVisible(Duration timeout) {
        try {
            getWait(timeout).until(
                    ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean notVisible() {
        return notVisible(defaultElementDuration);
    }

    public boolean waitForVisible(Duration timeout) {
        Wait<WebDriver> wait = new FluentWait<>(getPage().getDriver())
                .withTimeout(timeout)
                .pollingEvery(pollingDuration)
                .ignoreAll(List.of(NoSuchElementException.class, TimeoutException.class, NotFoundException.class));
        try {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return webElement.isDisplayed();
        } catch (TimeoutException exception){
            return false;
        }
    }

    public boolean waitForHidden(Duration timeout) {
        try {
            return getWait(timeout).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException exception){
            return false;
        }
    }

    public boolean waitForHidden() {
        return waitForHidden(defaultElementDuration);
    }

    public boolean waitForVisible() {
        return waitForVisible(defaultElementDuration);
    }

    public WebElement getWebElement() {
        if (webElement != null){
            return webElement;
        } else {
            if (parent != null)
                webElement = parent.getContainerAsElement().waitAndGetWebElement().findElement(locator);
            else
                webElement = waitAndGetWebElement();
        }
        return webElement;
    }


    protected WebElement waitAndGetWebElement(){
        return waitAndGetWebElement(defaultElementDuration);
    }

    private WebElement waitAndGetWebElement(Duration timeout){
        return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));//presence in DOM maybe more stable to use visibilityOfElementLocated
    }

    private Wait<WebDriver> getWait(Duration timeout){
        return new FluentWait<>(getPage().getDriver())
                .withTimeout(timeout)
                .pollingEvery(pollingDuration)
                .ignoreAll(List.of(NoSuchElementException.class, TimeoutException.class, NotFoundException.class));
    }

    protected final boolean waitForCondition(BooleanSupplier condition, Duration timeout) {
        return false;//todo selenium
    }

    public String getLogicalName() {
        return name;
    }

    public Rectangle getRect(){
        return getWebElement().getRect();
    }

    public BasePage getPage(){
        return page;
    }

    protected void ACTION(String info) {
        SeleniumSessions.getCurrentSession().getLoggerSession().ACTION(info);
    }

    protected void FATAL(String info) {
        SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(info);
    }

    protected void ACTION(String info, File screenshot) {
        SeleniumSessions.getCurrentSession().getLoggerSession().ACTION(info, screenshot);
    }

    protected void FATAL(String info, File screenshot) {
        SeleniumSessions.getCurrentSession().getLoggerSession().FATAL(info, screenshot);
    }

    public void clickWithOffset(int x, int y){
        ACTION(String.format("Click out of element '%s' with offset x: %d, y: %d", getLogicalName(), x, y));
        try{
            Actions action = new Actions(context.getDriver())
                    .moveToElement(getWebElement())
                    .moveByOffset(x, y)
                    .click();
            action.perform();
        } catch (Throwable e){
            logError(e.getMessage());
        }
    }

}