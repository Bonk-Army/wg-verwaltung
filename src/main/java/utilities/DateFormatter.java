package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class used to format a date to a localized date string
 */
public class DateFormatter {
    public static String dateTimeToString(Date date){
        String pattern = "dd.MM.yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);

        return df.format(date);
    }

    public static String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMMM yyyy");
        return formatter.format(date);
    }
}
