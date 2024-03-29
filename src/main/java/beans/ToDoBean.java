package beans;

import utilities.ErrorCodes;
import utilities.RegexHelper;
import utilities.SQLDCtodo;
import utilities.SQLDCusers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            if (!wgId.equals("")) {
                return SQLDCtodo.createTodo(task, userId, wgId, dateDue, createdById) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            } else {
                return ErrorCodes.NOWGFOUND;
            }
        }
        return ErrorCodes.WRONGENTRY;
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
     * Get all active todos where the current user is assigned
     *
     * @return A list of Todos
     */
    public List<Map<String, String>> getTodosForUser() {
        if (!wgId.isEmpty()) {
            return SQLDCtodo.getAllActiveTodosForUser(this.userId, this.wgId);
        }

        return new ArrayList<Map<String, String>>();
    }

    /**
     * Return a List of the names (Format: Max M for Max Mustermann) of the users in the wg of the specified user
     *
     * @return A List of Maps of which each contains the username and the formatted name string
     */
    public List<Map<String, String>> getUsersOfWg() {
        return SQLDCusers.getAllNameStringsForWg(this.wgId);
    }

    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @return Number of To-Do's for given User
     */
    public String getOpenTodosUser() {
        int openTodos = SQLDCtodo.getOpenTodosPerUser(this.userId, this.wgId);

        return String.valueOf(openTodos);
    }

    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @return Number of To-Do's for given WG
     */
    public String getOpenTodosWg() {
        int openTodos = SQLDCtodo.getOpenTodosPerWg(this.wgId);

        return String.valueOf(openTodos);
    }
}
