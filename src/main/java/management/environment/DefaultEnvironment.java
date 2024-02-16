package management.environment;

import java.time.Duration;
import java.util.Locale;

public class DefaultEnvironment extends BaseEnv {

    private static DefaultEnvironment env = new DefaultEnvironment();

    protected DefaultEnvironment() {
        super("/properties/default.properties");
    }

    public static DefaultEnvironment get() {
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
