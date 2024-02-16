package inst.ui.blocks;

import ui.IWebContext;
import ui.blocks.BaseBlock;
import ui.elements.Button;
import ui.elements.locator.Loc;

public class NotificationDialog extends BaseBlock {

    private final Button turnOnBtn = new Button("Turn On", this, Loc.xpath(".//button[text()='Turn On']"));
    private final Button notNow = new Button("Not Now", this, Loc.xpath(".//button[text()='Not Now']"));

    public NotificationDialog(IWebContext parent) {
        super("Notification Component", parent, Loc.xpath(".//div[@role='dialog' and .//span[contains(text(), 'Turn on notifications')]]"));
    }

    public void clickNotNow(){
        getLogger().info("Click Not Now");
        notNow.click();
    }

}
