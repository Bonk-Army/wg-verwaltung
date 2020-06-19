package beans;

import utilities.*;

import java.util.*;

public class ToDoBean {
    private String userId = "";

    public ToDoBean() {
    }

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
                return SQLDCTodo.createTodo(task, userId, wgId, dateDue, createdById) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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
        return DateFormatter.dateToString(date);
    }

    /**
     * Get all todos for the passed wg
     *
     * @param wgId The whId of the wg
     * @return The list of Todo Objects
     */
    public List<Map<String, String>> getAllTodos(String wgId) {
        return SQLDCTodo.getAllActiveTodos(wgId);
    }

    /**
     * Get the username of a user by his userId
     *
     * @return The username of the user
     */
    public String getUsername() {
        return SQLDCLogin.getUsername(this.userId);
    }

    /**
     * Return a list of all usernames of the users in the specified wg
     *
     * @param wgId The wgId of the wg
     * @return The list with usernames
     */
    public List<String> getAllUsersOfWG(String wgId) {
        return SQLDCTodo.getAllUsersOfWG(wgId);
    }

    /**
     * Return a List of all usernames of the users in the wg of the specified user
     *
     * @return The list with usernames
     */
    public List<Map<String, String>> getUsersOfWg() {
        String wgId = SQLDCTodo.getWgIdByUser(userId);

        List<String> usernames = SQLDCTodo.getAllUsersOfWG(wgId);
        List<Map<String, String>> formattedNames = new ArrayList<Map<String, String>>();

        for(String username: usernames){
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("username", username);
            userMap.put("nameString", getNameString(username));

            formattedNames.add(userMap);
        }

        return formattedNames;
    }

    /**
     * Returns the first name and the first letter of the last name of a user as one string
     *
     * @param username The username of the user
     * @return The String, e.g. Patrick M
     */
    public String getNameString(String username) {
        if (RegexHelper.checkString(username)) {
            return SQLDCTodo.getNameString(username);
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
        return SQLDCTodo.getWgIdByUser(userId);
    }

    /**
     * Set a todo to done
     *
     * @param todoId The todo to be set
     * @return If it was successful
     */
    public ErrorCodes setTodoDone(String todoId) {
        return SQLDCTodo.setTodoDone(todoId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
    }

    /**
     * Set a todo to inactive
     *
     * @param todoId The todo to be set
     * @return If it was successful
     */
    public ErrorCodes removeTodo(String todoId) {
        return SQLDCTodo.removeTodo(todoId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
    }

    // Getters and setters for access from JSP

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get all todos for a specific wg based on the currently logged in user
     *
     * @return The List of Todos
     */
    public List<Map<String, String>> getTodos() {
        String wgId = SQLDCTodo.getWgIdByUser(userId);

        return SQLDCTodo.getAllActiveTodos(wgId);
    }
}
