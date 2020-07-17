package utilities;

import models.FinancialEntry;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

/*
 Table structure:

        - uniqueID          (int)       (Primary key)
        - reason            (String)
        - value             (int)
        - dateCreated       (Date)
        - createdBy         (int)       (Foreign key to users.uniqueID)
        - isActive          (bool)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
 */

/**
 * Provides SQL Accessor methods for everything that accesses the financial table
 */
public class SQLDCfinancial extends SQLDatabaseConnection {
    /**
     * Create a new financial record
     *
     * @param reason     The reason for the entry
     * @param valueCents The total value of the purchase (in cents)
     * @param createdBy  The user that created the entry
     * @param wgId       The wgId of the wg in which the entry has to be issued
     * @return If it was successful
     */
    public static boolean createEntry(String reason, int valueCents, String createdBy, String wgId, Date date) {
        try {
            Timestamp dateStamp = new Timestamp(date.getTime());

            executeQuery(("INSERT INTO financial (reason, value, dateCreated, createdBy, isActive, wgId) "
                    + "VALUES ('" + reason + "', " + valueCents + ", '" + dateStamp
                    + "', " + Integer.valueOf(createdBy) + ", 1, " + Integer.valueOf(wgId) + ")"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns a List with all entries for the specified wg.
     * Each entry is saved into a map so we can directly access it in the frontend
     * This method also "looks" at the data and gives the required color info into the maps so we don' need to
     * have calculation for this in the frontend.
     * <p>
     * <b>USE THIS ONLY FOR DISPLAYING, NOT FOR CALCULATIONS AS THE VALUE IS CONVERTED TO A DOUBLE!!!</b>
     *
     * @param wgId The wgId of the wg for which the entries are returned
     * @return A List of maps of which each represents an entry
     */
    public static List<Map<String, String>> getAllActiveEntries(String wgId, int limit) {
        List<Map<String, String>> entries = new ArrayList<Map<String, String>>();

        try {
            // Returns all info for each financial entry that is active, ordered by their creation date
            ResultSet rs = executeQuery(("SELECT reason, value, dateCreated, createdBy, uniqueID FROM financial WHERE wgId="
                    + Integer.valueOf(wgId) + " AND isActive = 1 ORDER BY dateCreated DESC LIMIT " + limit));

            while (rs.next()) {
                Map<String, String> currentEntry = new HashMap<String, String>();

                currentEntry.put("reason", rs.getString(1));

                StringBuilder sb = new StringBuilder();
                Formatter formatter = new Formatter(sb, Locale.GERMAN);
                formatter.format("%,.2f", (rs.getInt(2) / 100d));
                String valueString = sb.toString();

                String createdDateString = DateFormatter.dateToString(rs.getTimestamp(3));

                if (rs.getInt(2) < 0) {
                    currentEntry.put("colorClass", "negative");
                } else {
                    currentEntry.put("colorClass", "positive");
                }

                currentEntry.put("value", valueString);
                currentEntry.put("dateCreated", createdDateString);
                String createdByUsername = SQLDCusers.getUsername(String.valueOf(rs.getInt(4)));
                currentEntry.put("createdBy", SQLDCusers.getNameString(createdByUsername));
                currentEntry.put("entryId", String.valueOf(rs.getInt(5)));

                entries.add(currentEntry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }

    /**
     * Set an entry to inactive
     *
     * @param entryId The entryId of the entry to be set to inactive
     * @return If it was successful
     */
    public static boolean setEntryInactive(String entryId) {
        try {
            executeQuery(("UPDATE financial SET isActive = 0 WHERE uniqueID=" + Integer.valueOf(entryId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return a list of FinancialEntry objects for <b>calculating</b> wg specific financial stats
     *
     * @param wgId The wgId of the wg
     * @return A List of FinancialEntry objects
     */
    public static List<FinancialEntry> getWgEntriesForCalculation(String wgId) {
        List<FinancialEntry> entries = new ArrayList<FinancialEntry>();

        try {
            ResultSet rs = executeQuery(("SELECT value, dateCreated, createdBy FROM financial WHERE wgId="
                    + Integer.valueOf(wgId) + " AND isActive = 1"));

            while (rs.next()) {
                int value = rs.getInt(1);
                Date dateCreated = rs.getTimestamp(2);
                String createdBy = String.valueOf(rs.getInt(3));

                entries.add(new FinancialEntry(dateCreated, value, createdBy));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }

    /**
     * Return a list of FinancialEntry objects for <b>calculating</b> user specific financial stats
     *
     * @param userId The userId of the user
     * @return A List of FinancialEntry objects
     */
    public static List<FinancialEntry> getUserEntriesForCalculation(String userId) {
        List<FinancialEntry> entries = new ArrayList<FinancialEntry>();

        try {
            ResultSet rs = executeQuery(("SELECT value, dateCreated FROM financial WHERE createdBy="
                    + Integer.valueOf(userId) + " AND isActive = 1"));

            while (rs.next()) {
                int value = rs.getInt(1);
                Date dateCreated = rs.getTimestamp(2);

                entries.add(new FinancialEntry(dateCreated, value, userId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entries;
    }

    /**
     * Return the sum of all entries of a user for the current wg
     *
     * @param userId The userId of the user
     * @param wgId   The wgId of the current wg of the user
     * @return An integer with the sum or 0 in case of an error
     */
    public static int getTotalForUser(String userId, String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT SUM(value) FROM financial WHERE createdBy = "
                    + Integer.valueOf(userId) + " AND isActive = 1 AND wgId = " + Integer.valueOf(wgId)));

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Return a map with the sum of expenses as value and the userId as key for every user of the wg
     *
     * @param wgId The wgId of the wg
     * @return A map with userId as key and sum as value
     */
    public static Map<String, Integer> getSumForEveryUserOfWg(String wgId) {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();

        try {
            ResultSet rs = executeQuery("SELECT SUM(value), createdBy FROM financial WHERE wgId = "
                    + Integer.valueOf(wgId) + " AND isActive = 1 GROUP BY createdBy");

            while (rs.next()) {
                String userId = String.valueOf(rs.getInt(2));
                int sum = rs.getInt(1);
                resultMap.put(userId, sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * Return the sum of all entries of a wg
     *
     * @param wgId The wgId of the wg
     * @return An integer with the sum or 0 in case of an error
     */
    public static int getTotalForWg(String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT SUM(value) FROM financial WHERE wgId = "
                    + Integer.valueOf(wgId) + " AND isActive = 1"));

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Get the wgId of an entry
     *
     * @param entryId The entryId of the entry
     * @return The wgId associated to the entry
     */
    public static String getWgIdOfEntry(String entryId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM financial WHERE uniqueID = " + Integer.valueOf(entryId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Get the subtotal balance for each month for the specified user for the last six months
     * This method always returns a list with size() == 6, filling it up with 0s if there is not enough data
     *
     * @param userId The userId of the user
     * @return The list with the total balance for each month
     */
    public static List<Integer> getBalanceDevelopmentForUser(String userId) {
        List<Integer> monthlyBalance = new ArrayList<Integer>();

        try {
            // Get the sum of expenses or income for each month in the last six months individually
            ResultSet rs = executeQuery(("SELECT SUM(value) FROM financial WHERE createdBy = " + Integer.valueOf(userId)
                    + " AND DATEDIFF(CURRENT_TIMESTAMP, dateCreated) <= 180 AND isActive = 1 GROUP BY EXTRACT(month FROM dateCreated)"));

            while (rs.next()) {
                monthlyBalance.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (monthlyBalance.size() < 6) {
            List<Integer> newList = new ArrayList<Integer>();

            for (int i = 0; i < (6 - monthlyBalance.size()); i++) {
                newList.add(0);
            }

            newList.addAll(monthlyBalance);

            monthlyBalance = new ArrayList<Integer>(newList);
        }

        return monthlyBalance;
    }
}
