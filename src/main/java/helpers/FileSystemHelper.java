package helpers;

import LOGGER.entities.LogLevels;
import lombok.extern.log4j.Log4j2;
import management.environment.LoggerEnvironment;
import management.playwright.run_management.Sessions;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.nio.file.Files;

public class FileSystemHelper {

    public static final String SCREENSHOT = "screenshot_";
    public static final String SLASH = "/";
    public static final String PNG = ".png";
    public static boolean isLoggerDirectoriesExisted = false;


    public static File createScreenshotFile(byte[] screenshot) {
        initLoggerDirectoryIfNeeded();

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
                //log.log(LogLevels.SYSTEM.getLevel(), "Directory '" + path + "' was created successfully.");
                System.out.println();
            else {
                //log.log(LogLevels.FATAL.getLevel(), "Failed to create directory '" + path + "'.");
                System.out.println();
                new Error("Failed to create directory.");
            }
        }
    }

    public static void initLoggerDirectoryIfNeeded() {
        if(!isLoggerDirectoriesExisted) {
            createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerDirectory());
            createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerScreenshotsDirectory());
        }
        isLoggerDirectoriesExisted = true;
    }

}
