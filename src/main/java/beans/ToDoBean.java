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
