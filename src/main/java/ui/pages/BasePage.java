package ui.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import lombok.Getter;
import management.environment.DefaultEnvironment;
import ui.IWebContext;
import ui.containers.BaseElementContainer;

import java.time.Duration;

public abstract class BasePage implements IWebContext {

    private Duration pageDuration = DefaultEnvironment.get().getNavigationPageTimeout();
    @Getter
    private String name;
    @Getter
    private Page pwPage;
    @Getter
    private BrowserContext pwContext;//can be deleted

    public BasePage(String name, BrowserContext context){
        this.name = name;
        pwPage = context.pages().get(0);
        pwContext = context;
    }

    public BasePage(String name, Page page){
        this.name = name;
        this.pwPage = page;
        pwContext = pwPage.context();
    }

    public abstract boolean waitForOpening(Duration duration);

    public boolean waitForOpening(){
        return waitForOpening(pageDuration);
    }

    public void refresh(){
        getPwPage().reload();
    }

    public BasePage getPage(){
        return this;
    }

    public BaseElementContainer getContainer(){
        return null;
    }

    //todo: need to learn more about this feature and how it works
    public Object evaluate(String expression){
        return getPwPage().evaluate(expression);
    }

}
