package utilities;

import models.TodoModel;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;
import java.sql.ResultSet;

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
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue, String createdById) {
        try {
            Date dateCreated = new Date();
            // Convert dates to java.sql.Timestamp to save them to SQL
            Timestamp createdStamp = new Timestamp(dateCreated.getTime());
            Timestamp dueStamp = new Timestamp(dateDue.getTime());

            ResultSet rs = executeQuery(("INSERT INTO todo (task, userId, wgId, dateCreated, dateDue, isDone, createdBy, isActive)"
                    + "VALUES ('" + task + "', " + Integer.valueOf(userId) + ", " + Integer.valueOf(wgId) + ", '"
                    + createdStamp + "', '" + dueStamp + "', " + false + ", " + Integer.valueOf(createdById) + ", 1)"));

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
    public static List<Map<String, String>> getAllActiveTodos(String wgId) {
        deactivateOldToDos();
        List<Map<String, String>> todoList = new ArrayList<Map<String, String>>();
        try {
            ResultSet rs = executeQuery(("SELECT task, userId, dateCreated, dateDue, isDone, isActive, createdBy, uniqueID FROM todo WHERE wgId = " + Integer.valueOf(wgId) + " ORDER BY isDone, dateDue ASC"));
            while (rs.next()) {
                Map<String, String> currentTodo = new HashMap<String, String>();
                currentTodo.put("task", rs.getString(1));
                currentTodo.put("userId", String.valueOf(rs.getInt(2)));
                currentTodo.put("dateCreated", rs.getDate(3).toString());
                currentTodo.put("dateDue", rs.getDate(4).toString());
                currentTodo.put("isDone", String.valueOf(rs.getBoolean(5)));
                currentTodo.put("createdBy", rs.getString(7));
                currentTodo.put("todoId", rs.getString(8));

                Boolean isActive = rs.getBoolean(6);
                if(isActive){
                    todoList.add(currentTodo);
                }
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
            ResultSet rs = executeQuery(("SELECT username FROM users WHERE wgId=" + Integer.valueOf(wgId) + " ORDER BY firstName ASC"));

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
            ResultSet rs = executeQuery("SELECT wgId FROM users WHERE uniqueID=" + Integer.valueOf(userId));

            while (rs.next()) {
                wgId = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wgId;
    }

    /**
     * Set an issue to status done
     *
     * @param todoId The id of the todo
     * @return If it was successful
     */
    public static boolean setTodoDone(String todoId) {
        try {
            executeQuery(("UPDATE todo SET isDone=true WHERE uniqueID=" + Integer.valueOf(todoId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Set an issue to status inactive
     *
     * @param todoId The id of the todo
     * @return If it was successful
     */
    public static boolean removeTodo(String todoId) {
        try {
            executeQuery(("UPDATE todo SET isActive=false WHERE uniqueID=" + Integer.valueOf(todoId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Concatenates First name and the first letter of the last name
     *
     * @param username The username of the user
     * @return The name string
     */
    public static String getNameString(String username) {
        String firstName;
        String lastName;
        try {
            firstName = SQLDCLogin.getFirstName(username);
            lastName = SQLDCLogin.getLastName(username);
            return firstName + " " + lastName.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * WGV-115
     * Deactivates done ToDos older than 30 days
     *
     */
    public static void deactivateOldToDos () {
        executeQuery("UPDATE todo SET isActive=0 WHERE isDone=1 AND dateDue < DATE_ADD(CURDATE(), INTERVAL -30 DAY);");
    }
}
