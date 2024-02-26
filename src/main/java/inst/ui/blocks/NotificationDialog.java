package inst.ui.blocks;

import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.Button;
import ui.elements.locator.Loc;

public class NotificationDialog extends BaseElementContainer {

    private final Button turnOnBtn = new Button("Turn On", this, Loc.xpath(".//button[text()='Turn On']"));
    private final Button notNow = new Button("Not Now", this, Loc.xpath(".//button[text()='Not Now']"));

    public NotificationDialog(IWebContext parent) {
        super("Notification Container", parent, Loc.xpath(".//div[@role='dialog' and .//span[contains(text(), 'Turn on notifications')]]"));
    }

    public void clickNotNow(){
        notNow.click();
    }

}
