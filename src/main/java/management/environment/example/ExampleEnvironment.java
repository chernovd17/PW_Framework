package management.environment.example;

import management.environment.BaseEnv;

import java.time.Duration;
import java.util.Locale;

public class ExampleEnvironment extends BaseEnv {

    private static ExampleEnvironment env = new ExampleEnvironment();

    protected ExampleEnvironment() {
        super("/properties/test_example.properties");
    }

    public static ExampleEnvironment get() {
        return env;
    }

    public String getAppUrl() {
        return getProperty("url");
    }

    public String getBrowserName() {
        return getProperty("browser");
    }

    public Duration getPageTimeout() {
        return Duration.ofSeconds(Integer.parseInt(getProperty("timeouts.page")));
    }

    public Duration getElementTimeout() {
        return Duration.ofSeconds(Integer.parseInt(getProperty("timeouts.element")));
    }

    public String getOS(){
        return System.getProperty("os.name");
    }

    public boolean isMac(){
        return getOS().toLowerCase(Locale.ROOT).contains("mac");
    }

}
