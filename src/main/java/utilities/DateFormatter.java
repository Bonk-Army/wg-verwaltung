package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class used to format a date to a localized date string
 */
public class DateFormatter {
    /**
     * Convert a given date to a localized String in the format 21. Juli 1969 03:56:00
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateTimeSecondsToString(Date date) {
        String pattern = "dd.MM.yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern, Locale.GERMAN);

        return df.format(date);
    }

    /**
     * Convert a given date to a localized String in the format 21. Juli 1969 03:56
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateTimeMinutesToString(Date date) {
        String pattern = "dd.MM.yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(pattern, Locale.GERMAN);

        return df.format(date);
    }

    /**
     * Convert a given date to a localized String in the format 21. Juli 1969
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMAN);
        return formatter.format(date);
    }

    /**
     * Get the current DateTime in MEZ/MESZ Time zone
     *
     * @return The Date object
     */
    public static Date getCurrentDateTime() {
        Instant instant = new Date().toInstant();
        ZoneId z = ZoneId.of("Europe/Berlin");
        ZonedDateTime zdt = instant.atZone(z);
        Date currentDate = Date.from(zdt.toInstant());

        return currentDate;
    }
}
