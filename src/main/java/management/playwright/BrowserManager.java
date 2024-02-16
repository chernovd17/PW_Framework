package management.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import management.environment.DefaultEnvironment;

import java.util.List;


public class BrowserManager {
    private Browser browser;
    private BrowserType.LaunchOptions options;
    private BrowserContext context;
    private final BrowserType browserType;//?

    public BrowserManager(BrowserType browserType, BrowserType.LaunchOptions options) {
        this.options = options;
        this.browserType = browserType;
        initContext();
    }

    public BrowserManager(BrowserType browserType, BrowserType.LaunchOptions options, List<Cookie> cookies) {
        this.options = options;
        this.browserType = browserType;
        initContext();
        addCookies(cookies);
    }

    public void addCookies(List<Cookie> cookies){
        context.addCookies(cookies);
    }

    private void initContext(){
        browser = browserType.launch(options);

        Browser.NewContextOptions opt = new Browser.NewContextOptions().setViewportSize(null);
        context = browser.newContext(opt);
    }

    public void navigate(String appUrl){
        Page page = context.newPage();

        page.setDefaultNavigationTimeout(DefaultEnvironment.get().getPageTimeout().toMillis());

        page.navigate(appUrl);
    }

    public static BrowserManager createNew(String browserName, BrowserType.LaunchOptions launchOptions) {

        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chrome":
                browserType = PlaywrightSession.getInstance().getPlaywright().chromium();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return new BrowserManager(browserType, launchOptions);

    }

    public static BrowserManager createNew(String browserName, BrowserType.LaunchOptions launchOptions, List<Cookie> cookies) {
        BrowserType type = getPWBrowserTypeByName(browserName);
        if(cookies.size() > 0)
            return new BrowserManager(type, launchOptions, cookies);
        else
            return new BrowserManager(type, launchOptions);
    }

    private static BrowserType getPWBrowserTypeByName(String browserName){
        BrowserType browserType;
        switch (browserName.toLowerCase()) {
            case "chrome":
                browserType = PlaywrightSession.getInstance().getPlaywright().chromium();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return browserType;
    }

    public void closeBrowser() {
        browser.close();
    }

    public Browser getBrowser(){
        return browser;
    }

    public BrowserContext getContext(){
        return context;
    }

    public Page closeLastTab(){
        context.pages().getLast().close();
        return context.pages().getLast();
    }
}
