package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the To Do Page
 */
public class ToDoBean {
    private String userId = "";
    private String wgId = "";

    public ToDoBean() {
    }

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
     * Create a to-do
     *
     * @param task    The task of the user
     * @param userId  The ID of the user
     * @param wgId    The ID of the WG
     * @param dateDue The date til the task should be done
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public ErrorCodes createTodo(String task, String userId, String wgId, Date dateDue, String createdById) {
        if (RegexHelper.checkText(task)) {
            if (RegexHelper.checkString(userId) && RegexHelper.checkString(wgId) && RegexHelper.checkString(createdById)) {
                return SQLDCtodo.createTodo(task, userId, wgId, dateDue, createdById) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }
        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Convert a Date object to a String
     *
     * @param date The Date object
     * @return The String
     */
    public String dateToString(Date date) {
        return DateFormatter.dateTimeToString(date);
    }

    /**
     * Return a list of all usernames of the users in the specified wg
     *
     * @param wgId The wgId of the wg
     * @return The list with usernames
     */
    public List<String> getAllUsersOfWG(String wgId) {
        return SQLDCusers.getAllUsersOfWG(wgId);
    }

    /**
     * Returns the first name and the first letter of the last name of a user as one string
     *
     * @param username The username of the user
     * @return The String, e.g. Max M
     */
    public String getNameString(String username) {
        if (RegexHelper.checkString(username)) {
            return SQLDCusers.getNameString(username);
        }

        return "";
    }

    /**
     * Return the wgId of the specified user
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public String getWgIdByUserId(String userId) {
        return SQLDCusers.getWgIdByUser(userId);
    }

    /**
     * Set a todo to done
     *
     * @param todoId The todo to be set
     * @return If it was successful
     */
    public ErrorCodes setTodoDone(String todoId, String wgId) {
        if (RegexHelper.checkString(todoId)) {
            String savedWgId = SQLDCtodo.getWgIdOfTodo(todoId);

            if (savedWgId.equals(wgId)) {
                return SQLDCtodo.setTodoDone(todoId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Set a todo to inactive if the user is allowed to
     *
     * @param todoId The todo to be set
     * @param wgId   The wgId of the user to check if it matches the saved wgId for that user
     * @return If it was successful
     */
    public ErrorCodes removeTodo(String todoId, String wgId) {
        if (RegexHelper.checkString(todoId)) {
            String savedWgId = SQLDCtodo.getWgIdOfTodo(todoId);

            if (savedWgId.equals(wgId)) {
                return SQLDCtodo.removeTodo(todoId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }

        return ErrorCodes.WRONGENTRY;
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

    /**
     * Set the user id parameter
     *
     * @param userId The user id of the user that is currently logged in
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    /**
     * Get the username of a user by his userId
     *
     * @return The username of the user
     */
    public String getUsername() {
        return SQLDCusers.getUsername(this.userId);
    }

    /**
     * Get all todos for a specific wg based on the currently logged in user
     *
     * @return The List of Todos
     */
    public List<Map<String, String>> getTodos() {
        return SQLDCtodo.getAllActiveTodos(this.wgId);
    }

    /**
     * Return a List of the names (Format: Max M for Max Mustermann) of the users in the wg of the specified user
     *
     * @return A List of Maps of which each contains the username and the formatted name string
     */
    public List<Map<String, String>> getUsersOfWg() {
        return SQLDCusers.getAllNameStringsForWg(this.wgId);
    }
}
