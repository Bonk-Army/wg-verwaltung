package utilities;

import models.TodoModel;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;

public class SQLDCTodo extends SQLDatabaseConnection {
    /**
     * Create a to-do in the database
     *
     * @param task    The task of the user
     * @param userId  The ID of the user
     * @param wgId    The ID of the WG
     * @param dateDue The date til the task should be done
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue) {
        try {
            Date dateCreated = new Date();
            ResultSet rs = executeQuery(("INSERT INTO todo (task, userId, wgId, dateCreated, dateDue, isDone)"
                    + "VALUES ('" + task + "', '" + userId + "', '" + wgId + "', '" + dateCreated + "', '" + dateDue + "', '" + false + "')"));

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
    public static ArrayList<TodoModel> getAllTodos(String wgId) {
        ArrayList<TodoModel> todoList = new ArrayList<>();
        try {
            ResultSet rs = executeQuery(("SELECT task, userId, dateCreated, dateDue, isDone FROM todo WHERE wgId = '" + wgId + "'"));
            while (rs.next()) {
                String task = rs.getString(1);
                String userId = rs.getString(2);
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
}
