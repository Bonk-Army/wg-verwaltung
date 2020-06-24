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
     * @return A list of maps of lists of maps (see datamodel below and code comments)
     * <p>
     * DATAMODEL: List with maps with lists with maps
     * REPRESENTS: rows[] -> info<>, days<> -> list[] with -> users<>
     * </p>
     */
    public static List<Map<String, List<Map<String, String>>>> getAllCleaningTasksForWg(String wgId) {
        List<Map<String, List<Map<String, String>>>> tasksList = new ArrayList<Map<String, List<Map<String, String>>>>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID, taskName, mondayUser, tuesdayUser, wednesdayUser, thursdayUser,"
                    + " fridayUser, saturdayUser, sundayUser FROM cleaning WHERE wgId = " + Integer.valueOf(wgId)));

            List<Map<String, String>> namesForWg = SQLDCusers.getAllNameStringsWithUserIdForWg(wgId);

            while (rs.next()) { // Each iteration is one row in the cleaning table
                Map<String, List<Map<String, String>>> currentTask = new HashMap<String, List<Map<String, String>>>();

                // General info list contains only one element: the general info map
                List<Map<String, String>> generalInfoList = new ArrayList<Map<String, String>>();
                Map<String, String> generalInfoMap = new HashMap<String, String>();
                generalInfoMap.put("taskId", String.valueOf(rs.getInt(1))); // Task ID
                generalInfoMap.put("taskname", rs.getString(2)); // Task Name
                generalInfoList.add(generalInfoMap);
                currentTask.put("general", generalInfoList);

                String weekdays[] = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

                // Iterate over each weekday. Create a new List for each weekday which contains the user info for the
                // dropdown menu. Then put that list in the currentTask map under the corresponding week day.
                for (int i = 0; i < 7; i++) {
                    String weekday = weekdays[i]; // Current weekday

                    // The fetched list of users for that wg
                    List<Map<String, String>> currentNamesForWg = namesForWg;
                    // The copy of that list to be sorted so the assigned user is at index 0
                    List<Map<String, String>> currentUserList = new ArrayList<Map<String, String>>();

                    String assignedUserId = rs.getString((3 + i));

                    // If no user is assigned, put an empty map at index 0. Otherwise, put the assigned user at
                    // index 0 and just append the rest of the list to the currentUserList
                    if (assignedUserId != null && assignedUserId.equals("")) {
                        currentUserList.add(new HashMap<String, String>());
                        currentUserList.addAll(currentNamesForWg);
                    } else {
                        for (Map<String, String> user : currentNamesForWg) {
                            if (user.get("userId").equals(assignedUserId)) {
                                currentUserList.add(user);
                                currentNamesForWg.remove(user);

                                currentUserList.addAll(currentNamesForWg);

                                break;
                            }
                        }
                    }

                    currentTask.put(weekday, currentUserList);
                }

                tasksList.add(currentTask);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasksList;
    }

    /**
     * Get the wgId associated with the given task
     *
     * @param taskId The taskId of the task
     * @return The wgId
     */
    public static String getWgIdForTask(String taskId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM cleaning WHERE uniqueID=" + Integer.valueOf(taskId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Get the ids of every task associated with the given wg
     *
     * @param wgId The wgId of the wg
     * @return The list of ids
     */
    public static List<String> getTaskIdsForWg(String wgId) {
        List<String> taskList = new ArrayList<String>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID FROM cleaning WHERE wgId=" + Integer.valueOf(wgId)));

            while (rs.next()) {
                taskList.add(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }
}
