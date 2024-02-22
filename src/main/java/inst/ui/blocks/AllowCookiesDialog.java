package inst.ui.blocks;

import inst.ui.pages.LoginPage;
import ui.IWebContext;
import ui.containers.BaseElementContainer;
import ui.elements.Button;
import ui.elements.locator.Loc;

public class AllowCookiesDialog extends BaseElementContainer {

    private final Button allowBtn = new Button("Allow Btn", this, Loc.xpath(".//button[text()='Allow all cookies']"));

    public AllowCookiesDialog(IWebContext parent) {
        super("Allow Cookies Dialog", parent, Loc.xpath(".//div[@role='dialog' and .//*[contains(text(), 'Allow the use of cookies from Instagram on this browser?')]]"));
    }

    public LoginPage allowCookies(){
        getLogger().info("Allow cookies");
        allowBtn.click();
        allowBtn.notVisible();
        return new LoginPage(getPwPage());
    }

}
