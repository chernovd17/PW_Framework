package helpers;

import LOGGER.withlog4j2.GlobalLoggerSession;
import LOGGER.entities.LogLevels;
import management.environment.LoggerEnvironment;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.nio.file.Files;

public class FileSystemHelper {

    public static final String SCREENSHOT = "screenshot_";
    public static final String SLASH = "/";
    public static final String PNG = ".png";


    public static File createScreenshotFile(byte[] screenshot) {
        String path = createUniqueScreenshotFilePath();

        File screenshotFile = new File(createUniqueScreenshotFilePath());
        try {
            Files.write(screenshotFile.toPath(), screenshot);
            return screenshotFile;
        } catch (java.io.IOException e) {
            throw new NotImplementedException("Problem with file '" + path + "' creation.");
        }
    }

    private static String createUniqueScreenshotFilePath(){
        long timeStamp = System.currentTimeMillis();
        String fileName = SCREENSHOT + timeStamp + PNG;
        return LoggerEnvironment.get().getLoggerScreenshotsDirectory() + SLASH + fileName;
    }

    public static void createDirectoryIfNeeded(String path){
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs())
                GlobalLoggerSession.getSession().getLogger().log(LogLevels.UNDEFINED.getLevel(), "Directory created successfully.");
            else
                GlobalLoggerSession.getSession().getLogger().log(LogLevels.UNDEFINED.getLevel(),"Failed to create directory.");
        }
    }

}
