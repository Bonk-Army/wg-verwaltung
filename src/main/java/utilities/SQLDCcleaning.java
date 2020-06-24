package utilities;

/*
 Table structure:

        - uniqueID          (int)
        - taskName          (String)
        - mondayUser        (int)       (Foreign key to users.uniqueID)
        - tuesdayUser       (int)       (Foreign key to users.uniqueID)
        - wednesdayUser     (int)       (Foreign key to users.uniqueID)
        - thursdayUser      (int)       (Foreign key to users.uniqueID)
        - firdayUser        (int)       (Foreign key to users.uniqueID)
        - saturdayUser      (int)       (Foreign key to users.uniqueID)
        - sundayUser        (int)       (Foreign key to users.uniqueID)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides SQL Accessor methods for everything that accesses the cleaning table
 */
public class SQLDCcleaning extends SQLDatabaseConnection {
    /**
     * Create a new cleaning task for the specified wg
     *
     * @param wgId     The wgId of the wg
     * @param taskName The task name (e.g.: "Küche putzen")
     * @return If it was successful
     */
    public static boolean addNewTask(String wgId, String taskName) {
        try {
            executeQuery(("INSERT INTO cleaning (taskName, wgId) VALUES "
                    + "('" + taskName + "', " + Integer.valueOf(wgId) + ")"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Update the assignes users for the specified task
     *
     * @param taskId The taskId of the task
     * @param mon    The user assigned for monday or empty if none assigned
     * @param tue    The user assigned for tuesday or empty if none assigned
     * @param wed    The user assigned for wednesday or empty if none assigned
     * @param thu    The user assigned for thursday or empty if none assigned
     * @param fri    The user assigned for friday or empty if none assigned
     * @param sat    The user assigned for saturday or empty if none assigned
     * @param sun    The user assigned for sunday or empty if none assigned
     * @return If it was successful
     */
    public static boolean updateUsersForTask(String taskId, String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
        int monInt = mon.isEmpty() ? null : Integer.valueOf(mon);
        int tueInt = tue.isEmpty() ? null : Integer.valueOf(tue);
        int wedInt = wed.isEmpty() ? null : Integer.valueOf(wed);
        int thuInt = thu.isEmpty() ? null : Integer.valueOf(thu);
        int friInt = fri.isEmpty() ? null : Integer.valueOf(fri);
        int satInt = sat.isEmpty() ? null : Integer.valueOf(sat);
        int sunInt = sun.isEmpty() ? null : Integer.valueOf(sun);
        try {
            executeQuery(("UPDATE cleaning SET mondayUser = " + monInt + ", tuesdayUser = " + tueInt
                    + ", wednesdayUser = " + wedInt + ", thursdayUser = " + thuInt + ", fridayUser = " + friInt
                    + ", saturdayUser = " + satInt + ", sundayUser = " + sunInt + " WHERE uniqueID = " + Integer.valueOf(taskId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get all cleaning tasks for a wg with their assignees.
     *
     * @param wgId The wgId of the wg
     * @return A list of maps of which each represents one task
     * <p>
     * <p>
     * DATAMODEL: List with maps with lists with maps
     * REPRESENTS: rows -> days -> list with -> users
     */
    public static List<Map<String, List<Map<String, String>>>> getAllCleaningTasksForWg(String wgId) {
        List<Map<String, List<Map<String, String>>>> tasksList = new ArrayList<Map<String, List<Map<String, String>>>>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID, taskName, mondayUser, tuesdayUser, wednesdayUser, thursdayUser,"
                    + " fridayUser, saturdayUser, sundayUser FROM cleaning WHERE wgId = " + Integer.valueOf(wgId)));

            List<Map<String, String>> namesForWg = SQLDCusers.getAllNameStringsWithUserIdForWg(wgId);

            while (rs.next()) {
                Map<String, Map<String, String>> currentTask = new HashMap<String, Map<String, String>>();

                Map<String, String> generalInfo = new HashMap<String, String>();
                generalInfo.put("cleanId", String.valueOf(rs.getInt(1)));
                generalInfo.put("title", rs.getString(2));

                currentTask.put("general", generalInfo);

                String weekdays[] = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
                for (int i = 0; i < 7; i++) {
                    String weekday = weekdays[i];
                    Map<String, String> currentDayData = new HashMap<String, String>();
                    List<Map<String, String>> currentNamesForWg = namesForWg;
                    List<Map<String, String>> currentUserList = new ArrayList<Map<String, String>>();

                    String assignedUserId = rs.getString((3 + i));

                    for (Map<String, String> user : currentNamesForWg) {
                        if (user.get("userId").equals(assignedUserId)) {
                            currentUserList.add(user);
                            currentNamesForWg.remove(user);

                            currentUserList.addAll(currentNamesForWg);

                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasksList;
    }
}
