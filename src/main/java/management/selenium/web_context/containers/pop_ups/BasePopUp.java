package management.selenium.web_context.containers.pop_ups;

import management.environment.DefaultEnvironment;
import management.selenium.web_context.IWebContext;
import management.selenium.web_context.containers.BaseElementContainer;
import org.openqa.selenium.By;

import java.time.Duration;

public abstract class BasePopUp extends BaseElementContainer {

    private static final Duration popUpDuration = DefaultEnvironment.get().getPopUpTimeout();

    public BasePopUp(String name, IWebContext parent, By loc) {
        super(name, parent, loc);
    }

    public boolean waitForOpening(Duration duration) {
        return getContainerAsElement().waitForVisible(duration);
    }

    public boolean waitForOpening() {
        return waitForOpening(popUpDuration);
    }

    public void closeByTapOutside(){
        STEP(String.format("Close %s By Click Outside", getContainerAsElement().getLogicalName()));
        getContainerAsElement().clickWithOffset(-10,-10);
    }

    public boolean waitForClose(){
        return getContainerAsElement().waitForHidden(popUpDuration);
    }
}
