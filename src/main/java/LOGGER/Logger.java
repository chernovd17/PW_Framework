package LOGGER;

public class Logger {

    private static Logger logger = null;

    private org.apache.log4j.Logger log;

    public void info(String info){
        log.info(info);
    }

    public void criticalError(String info){
        log.fatal(info);
    }

    public void notCriticalError(String info){
        log.error(info);
    }

    public void correct(String info){
        info(String.format("SUCCESS: %s", info));
    }

    public static Logger getLogger(){
        if(logger == null)
            logger = new Logger();
        return logger;
    }


}
