package ui.blocks;

import com.microsoft.playwright.Page;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import ui.IWebContext;
import ui.elements.Element;
import ui.elements.locator.Loc;
import ui.pages.BasePage;

import java.time.Duration;

@Log4j2
public abstract class BaseBlock implements IWebContext {

    private static final Duration defaultComponentDuration = Duration.ofSeconds(15);

    private Element element;
    private String name;
    private IWebContext parent;
    private Loc loc;

    public BaseBlock(String name, IWebContext parent, Loc loc){
        this.name = name;
        this.parent = parent;
        this.loc = loc;
        element = new Element(name, parent, loc);
    }

    @Override
    public boolean waitForOpening() {
        return waitForOpening(defaultComponentDuration);
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

    public BaseBlock getComponent(){
        return this;
    }

    public Element getComponentAsElement(){
        return element;
    }

    protected Logger getLogger() {
        return log;
    }

    public boolean visible() {
        return getComponent().getComponentAsElement().visible();
    }

    public boolean notVisible() {
        return !visible();
    }
}
