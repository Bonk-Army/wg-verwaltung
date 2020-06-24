package beans;

import utilities.ErrorCodes;
import utilities.RegexHelper;
import utilities.SQLDCcleaning;
import utilities.SQLDCusers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Bean used for the cleaning plan page
 */
public class CleanBean {
    private String userId = "";
    private String wgId = "";

    /*
      /$$$$$$                                 /$$             /$$
     /$$__  $$                               | $$            | $$
    | $$  \__/  /$$$$$$   /$$$$$$  /$$    /$$| $$  /$$$$$$  /$$$$$$
    |  $$$$$$  /$$__  $$ /$$__  $$|  $$  /$$/| $$ /$$__  $$|_  $$_/
     \____  $$| $$$$$$$$| $$  \__/ \  $$/$$/ | $$| $$$$$$$$  | $$
     /$$  \ $$| $$_____/| $$        \  $$$/  | $$| $$_____/  | $$ /$$
    |  $$$$$$/|  $$$$$$$| $$         \  $/   | $$|  $$$$$$$  |  $$$$/
     \______/  \_______/|__/          \_/    |__/ \_______/   \___/
     */
    // Methods used by servlets

    /**
     * Add a new cleaning task for the wg
     *
     * @param taskname The title / name of the task
     * @param wgId     The wgId of the wg
     * @return If it was successful
     */
    public ErrorCodes addNewTask(String taskname, String wgId) {
        if (RegexHelper.checkText(taskname)) {
            return SQLDCcleaning.addNewTask(wgId, taskname) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Update the assignes users for the given task if all entries are correct
     *
     * @param wgId   The wgId of the requesting user
     * @param taskId The taskId of the task
     * @param mon    The assigned user for monday
     * @param tue    The assigned user for tuesday
     * @param wed    The assigned user for wednesday
     * @param thu    The assigned user for thursday
     * @param fri    The assigned user for friday
     * @param sat    The assigned user for saturday
     * @param sun    The assigned user for sunday
     * @return If it was successful
     */
    public ErrorCodes updateUsersForTask(String wgId, String taskId, String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
        List<String> usersInWg = SQLDCusers.getAllUserIdsOfWg(wgId);
        usersInWg.add(""); // In case no one is assigned

        if (RegexHelper.checkString(taskId)) {
            // If the user is not allowed to edit this task, return an error
            if (!wgId.equals(SQLDCcleaning.getWgIdForTask(taskId))) {
                return ErrorCodes.WRONGENTRY;
            }

            // If a user that is not in the users wg is assigned, return an error
            if (!usersInWg.contains(mon) || !usersInWg.contains(tue) || !usersInWg.contains(wed) || !usersInWg.contains(thu)
                    || !usersInWg.contains(fri) || !usersInWg.contains(sat) || !usersInWg.contains(sun)) {
                return ErrorCodes.WRONGENTRY;
            }

            // If all entries are correct, update the task
            if (RegexHelper.checkString(mon) && RegexHelper.checkString(tue) && RegexHelper.checkString(wed)
                    && RegexHelper.checkString(thu) && RegexHelper.checkString(fri) && RegexHelper.checkString(sat) && RegexHelper.checkString(sun)) {
                SQLDCcleaning.updateUsersForTask(taskId, mon, tue, wed, thu, fri, sat, sun);
            }
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Get the task ids of the tasks associated with the wg
     *
     * @param wgId The wgId of the wg
     * @return The List of ids
     */
    public List<String> getTaskIdsForWg(String wgId) {
        if (RegexHelper.checkString(wgId)) {
            return SQLDCcleaning.getTaskIdsForWg(wgId);
        }

        return new ArrayList<>();
    }

    /*
      /$$$$$$              /$$     /$$                                               /$$        /$$$$$$              /$$     /$$
     /$$__  $$            | $$    | $$                                              /$$/       /$$__  $$            | $$    | $$
    | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$           /$$/       | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$
    | $$ /$$$$ /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/          /$$/        |  $$$$$$  /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/
    | $$|_  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$          /$$/          \____  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$
    | $$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$        /$$/           /$$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$
    |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/       /$$/           |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/
     \______/  \_______/   \___/   \___/   \_______/|__/      |_______/       |__/             \______/  \_______/   \___/   \___/   \_______/|__/      |_______/
    */

    // Getters and Setters for use with JSPs

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    /**
     * Return the cleaning data for the current users wg. Quite complicated data model, see
     * SQLDCcleaning.getAllCleaningTasksForWG documentation for better understanding
     *
     * @return The data
     */
    public List<Map<String, List<Map<String, String>>>> getCleanData() {
        return SQLDCcleaning.getAllCleaningTasksForWg(this.wgId);
    }

    /**
     * Return a List of the names (Format: Max M for Max Mustermann) of the users in the wg of the specified user
     *
     * @return A List of Maps of which each contains the username and the formatted name string
     */
    public List<Map<String, String>> getUsersOfWg() {
        List<Map<String, String>> users = SQLDCusers.getAllNameStringsWithUserIdForWg(this.wgId);

        Map<String, String> emptyUser = new HashMap<String, String>();
        emptyUser.put("userId", "");
        emptyUser.put("nameString", "");

        users.add(emptyUser);

        return users;
    }
}
