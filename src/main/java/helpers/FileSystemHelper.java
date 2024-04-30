package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logger_and_report.entities.SuiteInfo;
import management.environment.LoggerEnvironment;
import management.playwright.run_management.Sessions;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemHelper {

    public static final String SCREENSHOT = "screenshot_";
    public static final String SUITE_INFO = "suite_info_";
    public static final String SLASH = "/";
    public static final String PNG = ".png";
    public static final String JSON = ".json";
    public static boolean isLoggerDirectoriesExisted = false;

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        initLoggerDirectoryIfNeeded();
    }

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
        return Sessions.getCurrentSession().getLoggerSession().getTestInfo().getScreenshotFolder() + SLASH + fileName;
    }

    public static void upsertSuiteInfoFile(SuiteInfo suiteInfo)  {
        try {
            var json = mapper.writeValueAsBytes(suiteInfo);
            var path = createUniqueSuiteFilePath(suiteInfo);
            Files.write(path, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createUniqueSuiteFilePath(SuiteInfo suiteInfo){
        String fileName = suiteInfo.getTitle();
        return Path.of(LoggerEnvironment.get().getLoggerSuiteInfoDirectory() + SLASH + fileName);
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
            createDirectoryIfNeeded(LoggerEnvironment.get().getLoggerSuiteInfoDirectory());
        }
        isLoggerDirectoriesExisted = true;
    }

    public static String createScreenshotFilePath(String fileName) {
        String path = LoggerEnvironment.get().getLoggerScreenshotsDirectory() + SLASH + fileName + System.currentTimeMillis();
        createDirectoryIfNeeded(path);
        return path;
    }

}
