package management.playwright;

import logger_and_report.withlog4j2.TestLogger;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import helpers.FileSystemHelper;
import lombok.Getter;
import management.environment.DefaultEnvironment;
import management.environment.LoggerEnvironment;
import management.playwright.run_management.Sessions;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;


public class BrowserManager {
    @Getter
    private Browser browser;
    private final BrowserType.LaunchOptions options;
    @Getter
    private BrowserContext context;//todo need to create as List -- it's useful for tests with 2+ windows
    private final BrowserType browserType;//?
    private Playwright playwright;

    public BrowserManager(BrowserType browserType, BrowserType.LaunchOptions options, Playwright playwright) {
        this.options = options;
        this.browserType = browserType;
        this.playwright = playwright;
        initContext();
    }

    public void closeDriverOrPw(){
        if(playwright != null)
            playwright.close();
    }

    public synchronized void closeBrowser() {
        browser.close();
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

        Browser.NewContextOptions opt = initContextOptions();
        context = browser.newContext(opt);
    }

    public Browser.NewContextOptions initContextOptions(){
        Browser.NewContextOptions opt = new Browser.NewContextOptions().setViewportSize(null).setLocale("en-GB");
        if(DefaultEnvironment.get().withVideo()) {
            opt.setRecordVideoDir((Paths.get(LoggerEnvironment.get().getLoggerVideoDirectory())));
            opt.setRecordVideoSize(640, 480);
        }
        return opt;
    }

    public synchronized void navigate(String appUrl){
        getLogger().SYSTEM("Navigate to " + appUrl);
        Page page = context.newPage();

        page.setDefaultNavigationTimeout(DefaultEnvironment.get().getNavigationPageTimeout().toMillis());//30 seconds is default
        page.setDefaultTimeout(DefaultEnvironment.get().getPageTimeout().toMillis());//1 millisecond is default

        page.navigate(appUrl);
    }

    public static BrowserManager createNew(String browserName, BrowserType.LaunchOptions launchOptions) {

        BrowserType browserType;
        Playwright playwright = Playwright.create();
        switch (browserName.toLowerCase()) {
            case "chrome":
                //browserType = PlaywrightSession.getInstance().getPlaywright().chromium();
                browserType = playwright.chromium();

                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return new BrowserManager(browserType, launchOptions, playwright);

    }

    public Page closeLastTab(){
        context.pages().getLast().close();
        return context.pages().getLast();
    }

    public File makeScreenshot(){

        Page page = getContext().pages().getLast();//now I don't know how to get active/required window, so taking last
        byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return FileSystemHelper.createScreenshotFile(buffer);
    }

    public TestLogger getLogger() {
        return Sessions.getCurrentSession().getLoggerSession();
    }
}
