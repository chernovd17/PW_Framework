package inst.helpers;

import java.io.*;
import java.net.URL;

public class FileHelper {

    public static final String SPACE = "\\s+";
    public static final String SEPARATOR_COMMA = ",";

    public static File getFileFromResource(String fileShortPath){
        URL url = FileHelper.class.getResource(fileShortPath);//todo need to change
        if(url == null)
            return null;
        else
            return new File(url.getFile());
    }
}
