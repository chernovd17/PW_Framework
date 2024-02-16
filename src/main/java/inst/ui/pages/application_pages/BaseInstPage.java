package inst.ui.pages.application_pages;


import com.microsoft.playwright.Page;
import ui.IWebContext;
import ui.pages.BasePage;


public abstract class BaseInstPage extends BasePage {

    /*private LeftMenuComponent leftMenu;

    private Button closeBtn = new Button("Close Dialogs", this, Loc.css("svg[aria-label='Close']"));*/

    protected BaseInstPage(String name, IWebContext iWebContext) {
        super(name, iWebContext.getPwPage());
        //leftMenu = new LeftMenuComponent(this);
    }

    protected BaseInstPage(String name, Page pwPage) {
        super(name, pwPage);
        //leftMenu = new LeftMenuComponent(this);
    }

     /*public ProfilePage openProfile(){
        return leftMenu.openProfilePage();
    }

    public ImageUploadDialogWindow openCreatePostDialog(){
        return leftMenu.openCreateContent();
    }

    public void closeDialogs(){
        if(closeBtn.visible())
            closeBtn.click();
        else {
            //todo need to implement close by tap coordinates
        }
    }

    protected LeftMenuComponent getLeftMenu(){
        return leftMenu;
    }

    public HomePage openHomePage() {
        return getLeftMenu().openHomePage();
    }*/
}
