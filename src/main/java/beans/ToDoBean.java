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
    public ErrorCodes createTodo(String task, String userId, String wgId, Date dateDue) {
        if (RegexHelper.checkString(task)) {
            return SQLDCTodo.createTodo(task, userId, wgId, dateDue) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }
        return ErrorCodes.WRONGENTRY;
    }

    public String dateToString(Date date) {
        return DateFormatter.dateToString(date);
    }

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
     * Return the wgId of the specified user
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public String getWgIdByUserId(String userId) {
        return SQLDCTodo.getWgIdByUser(userId);
    }
}
