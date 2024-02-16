package inst.ui.pages.application_pages;

import com.microsoft.playwright.Page;
import ui.IWebContext;

import java.time.Duration;

public class HomePage extends BaseInstPage{

    public HomePage(IWebContext pageContext) {
        super("First Page After Login", pageContext);
    }

    public HomePage(Page pwPage) {
        super("First Page After Login", pwPage);
    }

    @Override
    public boolean waitForOpening(Duration timeout) {
        //return getLeftMenu().waitForOpening(timeout);
        return true;
    }
}
