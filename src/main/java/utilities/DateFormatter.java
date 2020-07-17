package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);

        return formatter.format(date);
    }

    /**
     * Convert a given date to a localized String in the format 21. Juli 1969 03:56
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateTimeMinutesToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);

        return formatter.format(date);
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
     * Get the current Date
     * Because we work on Azure which is in a different time zone, we have to adjust the Date.
     * Thats why it returns the correct Date on Azure but probably a wrong date when testing locally.
     *
     * @return The Date object
     */
    public static Date getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN);
        formatter2.setTimeZone(TimeZone.getTimeZone("GMT"));

        String formattedDateString = formatter.format(new Date());

        Date adjustedDate;

        try {
            adjustedDate = formatter2.parse(formattedDateString);
        } catch (Exception e) {
            e.printStackTrace();
            adjustedDate = new Date();
        }

        return adjustedDate;
    }
}
