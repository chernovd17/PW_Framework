package helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeSystemHelper {

    public static final String SCREENSHOT_PATTERN = "dd-MM-yyyy_hh:mm:ss";

    public static String convertDateTimeToString(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static String convertDateToString(LocalDate localDate, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }
}
