package utilities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);

        Instant instant = date.toInstant();
        ZoneId z = ZoneId.of("Europe/Berlin");
        ZonedDateTime zdt = instant.atZone(z);

        return zdt.format(formatter);
    }

    /**
     * Convert a given date to a localized String in the format 21. Juli 1969 03:56
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateTimeMinutesToString(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.GERMAN);

        Instant instant = date.toInstant();
        ZoneId z = ZoneId.of("Europe/Berlin");
        ZonedDateTime zdt = instant.atZone(z);

        return zdt.format(formatter);
    }

    /**
     * Convert a given date to a localized String in the format 21. Juli 1969
     *
     * @param date The date to be formatted
     * @return The formatted string
     */
    public static String dateToString(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy", Locale.GERMAN);

        Instant instant = date.toInstant();
        ZoneId z = ZoneId.of("Europe/Berlin");
        ZonedDateTime zdt = instant.atZone(z);

        return zdt.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(dateTimeSecondsToString(new Date()));
    }

    /**
     * Get the current Date
     *
     * @return The Date object
     */
    public static Date getCurrentDateTime() {
        return new Date();
    }
}
