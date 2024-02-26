package management.environment;

import lombok.Getter;

@Getter
public class LoggerEnvironment extends BaseEnv {

    private static LoggerEnvironment env;
    private boolean loggerIsEnabled;
    private String loggerDirectory;
    private String loggerScreenshotsDirectory;

    protected LoggerEnvironment() {
        super("/properties/logger.properties");
        loggerIsEnabled = Boolean.parseBoolean(getProperty("logger.enabling"));
        loggerDirectory = getProperty("logger.main_folder");
        loggerScreenshotsDirectory = getProperty("logger.screenshots_folder");

    }

    public static LoggerEnvironment get() {
        if(env == null)
            env = new LoggerEnvironment();
        return env;
    }
}
