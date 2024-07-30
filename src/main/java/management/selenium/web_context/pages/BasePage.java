package management.selenium.web_context.pages;

import lombok.Getter;
import management.environment.DefaultEnvironment;
import management.selenium.helpers.Verification;
import management.selenium.web_context.containers.BaseElementContainer;
import management.selenium.web_context.IWebContext;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BasePage implements IWebContext {

    private static final Duration pageDuration = DefaultEnvironment.get().getNavigationPageTimeout();

    @Getter
    protected WebDriver driver;
    @Getter
    private String name;

    public BasePage(String name, WebDriver driver) {
        this.name = name;
        this.driver = driver;
    }

    public abstract boolean waitForOpening(Duration duration);

    public boolean waitForOpening(){
        return waitForOpening(pageDuration);
    }

    public void waitAndVerifyPageIsOpened(){
        Verification.verifyTrue(waitForOpening(), String.format("Verify if page %sis opened", name));
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public BaseElementContainer getContainer(){
        return null;
    }

    public BasePage getPage(){
        return this;
    }

}
