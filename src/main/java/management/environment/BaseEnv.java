package management.environment;

import java.io.IOException;
import java.util.Properties;

public abstract class BaseEnv {

    private Properties defProp;

    protected BaseEnv(String path) {
        defProp = new Properties();
        try {
            defProp.load(DefaultEnvironment.class.getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String propName) {
        return defProp.getProperty(propName, System.getProperty(propName));//System.getProperty(propName) -- if need to use "-Dproperty=''"
    }
}
