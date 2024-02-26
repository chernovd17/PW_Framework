package ui.containers;

import com.microsoft.playwright.Page;
import ui.IWebContext;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.time.Duration;

public abstract class BaseElementContainer implements IWebContext {

    private static final Duration defaultConteinerDuration = Duration.ofSeconds(15);

    private Element element;
    private String name;
    private IWebContext parent;
    private Loc loc;

    public BaseElementContainer(String name, IWebContext parent, Loc loc){
        this.name = name;
        this.parent = parent;
        this.loc = loc;
        element = new Element(name, parent, loc);
    }

    @Override
    public boolean waitForOpening() {
        return waitForOpening(defaultConteinerDuration);
    }

    @Override
    public boolean waitForOpening(Duration timeout) {
        return element.waitForVisible(timeout);
    }

    @Override
    public void refresh() {
        getPage().refresh();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Page getPwPage() {
        return parent.getPwPage();
    }

    @Override
    public BasePage getPage() {
        return parent.getPage();
    }

    public BaseElementContainer getContainer(){
        return this;
    }

    public Element getContainerAsElement(){
        return element;
    }

    public boolean visible() {
        return getContainer().getContainerAsElement().visible();
    }

    public boolean notVisible() {
        return !visible();
    }

}
