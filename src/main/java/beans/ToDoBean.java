package beans;

import models.TodoModel;
import utilities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoBean {
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
    public List<TodoModel> getAllTodos(String wgId) {
        return SQLDCTodo.getAllTodos(wgId);
    }

    /**
     * Get all todos for a specific wg based on the currently logged in user
     *
     * @param sessionIdentifier The session identifier of the logged in user
     * @return The List of Todos
     */
    public List<TodoModel> getAllTodosBySessionIdentifier(String sessionIdentifier) {
        if (RegexHelper.checkString(sessionIdentifier) && !sessionIdentifier.isEmpty()) {
            int splitIndex = sessionIdentifier.indexOf('-');
            String userId = sessionIdentifier.substring(0, splitIndex);
            String cookiePostfix = sessionIdentifier.substring(splitIndex + 1, sessionIdentifier.length());

            String username = SQLDCLogin.getUsername(userId);
            String savedCookiePostfix = SQLDCLogin.getCookiePostfix(username);

            // If the passed postfix matches the saved one, fetch the todos and return them
            if (savedCookiePostfix.equals(cookiePostfix)) {
                String wgId = SQLDCTodo.getWgIdByUser(userId);

                return SQLDCTodo.getAllTodos(wgId);
            }
        }

        return new ArrayList<TodoModel>();
    }

    /**
     * Return a List of the usernames of all users in the same wg as the user whos session identifier is passed
     *
     * @param sessionIdentifier The session identifier of the current user
     * @return The list of all usernames
     */
    public List<String> getAllUsersOfWgBySessionIdentifier(String sessionIdentifier) {
        if (RegexHelper.checkString(sessionIdentifier) && !sessionIdentifier.isEmpty()) {
            int splitIndex = sessionIdentifier.indexOf('-');
            String userId = sessionIdentifier.substring(0, splitIndex);
            return getAllUsersOfWgByUserId(userId);
        }

        return new ArrayList<String>();
    }

    /**
     * Get the username of a user by his userId
     *
     * @param userId The userId of the user
     * @return The username of the user
     */
    public String getUsername(String userId) {
        return SQLDCLogin.getUsername(userId);
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
     * @param userId The user to return the other usernames for
     * @return The list with usernames
     */
    public List<String> getAllUsersOfWgByUserId(String userId) {
        String wgId = SQLDCTodo.getWgIdByUser(userId);

        return SQLDCTodo.getAllUsersOfWG(wgId);
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
}
