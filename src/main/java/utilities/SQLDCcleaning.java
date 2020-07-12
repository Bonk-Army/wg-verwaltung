package utilities;

/*
 Table structure:

        - uniqueID          (int)       (Primary key)
        - taskName          (String)
        - mondayUser        (int)       (Foreign key to users.uniqueID)
        - tuesdayUser       (int)       (Foreign key to users.uniqueID)
        - wednesdayUser     (int)       (Foreign key to users.uniqueID)
        - thursdayUser      (int)       (Foreign key to users.uniqueID)
        - firdayUser        (int)       (Foreign key to users.uniqueID)
        - saturdayUser      (int)       (Foreign key to users.uniqueID)
        - sundayUser        (int)       (Foreign key to users.uniqueID)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
        - isActive          (bool)
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
            executeQuery(("INSERT INTO cleaning (taskName, wgId, isActive) VALUES "
                    + "('" + taskName + "', " + Integer.valueOf(wgId) + ", 1)"));

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
        Integer monInt = mon.isEmpty() ? null : Integer.valueOf(mon);
        Integer tueInt = tue.isEmpty() ? null : Integer.valueOf(tue);
        Integer wedInt = wed.isEmpty() ? null : Integer.valueOf(wed);
        Integer thuInt = thu.isEmpty() ? null : Integer.valueOf(thu);
        Integer friInt = fri.isEmpty() ? null : Integer.valueOf(fri);
        Integer satInt = sat.isEmpty() ? null : Integer.valueOf(sat);
        Integer sunInt = sun.isEmpty() ? null : Integer.valueOf(sun);
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
     * This fetches the data from sql and then parses it to the data structure that we need in the frontend.
     * The parsed structure is a list of maps. Each of these maps represents one row in the frontend and therefore
     * contains all required info for exactly one cleaning task. Now each of these maps contains other lists.
     * In the first list, mapped under the key "general", we find a map with general info at index 0. The other lists,
     * each mapped under a weekday name, contain the possible assignees for this task. These lists hence contain maps
     * where each map represents one user.
     *
     * @param wgId The wgId of the wg
     * @return A list of maps of lists of maps (see datamodel below and code comments)
     * <p>
     * DATAMODEL: List with maps with lists with maps
     * REPRESENTS: rows[] -> info<>, days<> -> list[] with -> users<>
     * </p>
     */
    public static List<Map<String, List<Map<String, String>>>> getAllCleaningTasksForWg(String wgId) {
        /*
        The graph below shows the structure. |--* means many children, |--1 means one child and so on

        List (tasks)
             |
             |--* Map (Task)
                     |
                     |--1 List (General Info)
                     |       |
                     |       |--1 Map (General Info)
                     |
                     |--7 List (Users for Weekday)
                             |--* Map (User Info)
         */
        List<Map<String, List<Map<String, String>>>> tasksList = new ArrayList<Map<String, List<Map<String, String>>>>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID, taskName, mondayUser, tuesdayUser, wednesdayUser, thursdayUser,"
                    + " fridayUser, saturdayUser, sundayUser FROM cleaning WHERE wgId = " + Integer.valueOf(wgId)
                    + " AND isActive = 1"));

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
                    List<Map<String, String>> currentNamesForWg = new ArrayList<>(namesForWg);
                    // The copy of that list to be sorted so the assigned user is at index 0
                    List<Map<String, String>> currentUserList = new ArrayList<Map<String, String>>();

                    String assignedUserId = rs.getString((3 + i));

                    // If no user is assigned, put an empty map at index 0. Otherwise, put the assigned user at
                    // index 0 and just append the rest of the list to the currentUserList
                    if (assignedUserId == null || assignedUserId.equals("")) {
                        currentUserList.add(new HashMap<String, String>());
                        currentUserList.addAll(currentNamesForWg);
                    } else {
                        for (Map<String, String> user : currentNamesForWg) {
                            if (user.get("userId").equals(assignedUserId)) {
                                currentUserList.add(user);
                                currentUserList.add(new HashMap<String, String>());
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
            ResultSet rs = executeQuery(("SELECT uniqueID FROM cleaning WHERE wgId=" + Integer.valueOf(wgId)
                    + " AND isActive = 1"));

            while (rs.next()) {
                taskList.add(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }

    /**
     * Set the given task as inactive
     *
     * @param taskId The taskId of the task
     * @return If it was successful
     */
    public static boolean setTaskDone(String taskId) {
        try {
            executeQuery(("UPDATE cleaning SET isActive = 0 WHERE uniqueID=" + Integer.valueOf(taskId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
