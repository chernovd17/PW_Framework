package management.selenium.sessions_management;

import helpers.FileSystemHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import logger_and_report.withlog4j2.TestLogger;
import lombok.Getter;
import management.environment.DefaultEnvironment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SeleniumBrowserManager {

    private ChromeOptions chromeOptions;
    @Getter
    private WebDriver driver;

    //todo need to re-implement for different Browsers
    private SeleniumBrowserManager(ChromeOptions chromeOptions) {
        this.chromeOptions = chromeOptions;
        this.driver = new ChromeDriver(chromeOptions);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(DefaultEnvironment.get().getPageTimeout());
        //this.driver.manage().timeouts().implicitlyWait(DefaultEnvironment.get().getElementTimeout());
    }

    public static SeleniumBrowserManager createNew(String browserName) {
        WebDriverManager.chromedriver().setup();
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new SeleniumBrowserManager(createDefaultChromeOptions());
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    private static ChromeOptions createDefaultChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        options.addArguments("disable-popup-blocking");
        options.addArguments("−−lang=en-US");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-search-engine-choice-screen");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "en");
        options.setExperimentalOption("prefs", prefs);
        return options;
    }


    public synchronized void closeBrowser() {
        driver.close();
    }

    public synchronized void closeDriver() {
        driver.quit();
    }

    public File makeScreenshot(){
        return FileSystemHelper.createSeleniumScreenshotFile(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
    }

    public TestLogger getLogger() {
        return SeleniumSessions.getCurrentSession().getLoggerSession();
    }

    public void navigate(String appUrl) {
        getLogger().SYSTEM("Navigate to " + appUrl);
        driver.get(appUrl);
    }

}
