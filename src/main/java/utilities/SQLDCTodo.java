package utilities;

import models.TodoModel;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.util.List;

public class SQLDCTodo extends SQLDatabaseConnection {
    /**
     * Create a to-do in the database
     *
     * @param task    The task of the user
     * @param userId  The ID of the user that is assigned to this task
     * @param wgId    The ID of the WG
     * @param dateDue The date til the task should be done
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue) {
        try {
            Date dateCreated = new Date();
            ResultSet rs = executeQuery(("INSERT INTO todo (task, userId, wgId, dateCreated, dateDue, isDone)"
                    + "VALUES ('" + task + "', " + Integer.valueOf(userId) + ", '" + wgId + "', '" + dateCreated + "', '" + dateDue + "', '" + false + "')"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return all To-Dos for a wg
     *
     * @param wgId the ID of the wg
     * @return ArrayList<TodoModel>
     */
    public static List<TodoModel> getAllTodos(String wgId) {
        List<TodoModel> todoList = new ArrayList<>();
        try {
            ResultSet rs = executeQuery(("SELECT task, userId, dateCreated, dateDue, isDone FROM todo WHERE wgId = '" + wgId + "'"));
            while (rs.next()) {
                String task = rs.getString(1);
                String userId = String.valueOf(rs.getInt(2));
                Date dateCreated = rs.getDate(3);
                Date dateDue = rs.getDate(4);
                Boolean isDone = rs.getBoolean(5);
                TodoModel todoModel = new TodoModel(task, userId, wgId, dateCreated, dateDue, isDone);
                todoList.add(todoModel);
            }
            return todoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return a list with all usernames of the users in the specified WG
     *
     * @param wgId The wgId of the WG
     * @return The list of usernames
     */
    public static List<String> getAllUsersOfWG(String wgId) {
        List<String> users = new ArrayList<String>();

        try {
            ResultSet rs = executeQuery(("SELECT username FROM users WHERE wgId=" + wgId));

            while (rs.next()) {
                users.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Get the wgId for the specified user
     *
     * @param userId The userId of the user
     * @return The wgId as a String
     */
    public static String getWgIdByUser(String userId) {
        String wgId = "";

        try {
            ResultSet rs = executeQuery("SELECT wgId FROM users WHERE uniqueID=" + userId);

            while (rs.next()) {
                rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wgId;
    }
}
