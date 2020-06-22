package utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.ResultSet;

/*
 Table structure:

        - uniqueID          (int)
        - isActive          (bool)
        - task              (String)
        - userId            (int)       (Foreign key to users.uniqueID)
        - dateCreated       (Date)
        - dateDue           (Date)
        - isDone            (bool)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
        - createdBy         (int)       (Foreign key to users.uniqueID)
 */

/**
 * Provides SQL accessor methods for everything that accesses the todo table
 */
public class SQLDCtodo extends SQLDatabaseConnection {
    /**
     * Create a to-do in the database
     *
     * @param task        The task of the user
     * @param userId      The ID of the user that is assigned to this task
     * @param wgId        The ID of the WG
     * @param dateDue     The date til the task should be done
     * @param createdById The id of the user that created the todo
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue, String createdById) {
        try {
            Date dateCreated = new Date();
            // Convert dates to java.sql.Timestamp to save them to SQL
            Timestamp createdStamp = new Timestamp(dateCreated.getTime());
            Timestamp dueStamp = new Timestamp(dateDue.getTime());

            ResultSet rs = executeQuery(("INSERT INTO todo (task, userId, wgId, dateCreated, dateDue, isDone, createdBy, isActive) "
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
            ResultSet rs = executeQuery(("SELECT task, userId, dateCreated, dateDue, isDone, isActive, createdBy, uniqueID FROM todo WHERE wgId = "
                    + Integer.valueOf(wgId) + " AND isActive = 1 ORDER BY isDone, dateDue ASC"));
            while (rs.next()) {
                Map<String, String> currentTodo = new HashMap<String, String>();
                currentTodo.put("task", rs.getString(1));
                currentTodo.put("isDone", String.valueOf(rs.getBoolean(5)));
                currentTodo.put("todoId", rs.getString(8));

                // Parameters for better visualization of the status of every todo

                // Dates for colors
                Date dateDue = rs.getDate(4);
                Date dateCreated = rs.getDate(3);
                Date currentDate = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 3);
                Date threeDaysDate = c.getTime();

                SimpleDateFormat fancyFormatter = new SimpleDateFormat("dd. MMMM yyyy");
                currentTodo.put("dateDue", fancyFormatter.format(dateDue));
                currentTodo.put("dateCreated", fancyFormatter.format(dateCreated));

                Boolean isDone = rs.getBoolean(5);
                if (isDone) {
                    currentTodo.put("doneMessage", "Ja");
                    currentTodo.put("buttonHideStatus", "hidden=\"hidden\"");
                    currentTodo.put("colorClass", "done");
                } else {
                    currentTodo.put("doneMessage", "Nein");
                    currentTodo.put("buttonHideStatus", "");
                    if (currentDate.after(dateDue)) {
                        currentTodo.put("colorClass", "notDone tooLate");
                    } else if (threeDaysDate.after(dateDue)) {
                        currentTodo.put("colorClass", "notDone late");
                    } else {
                        currentTodo.put("colorClass", "notDone");
                    }
                }

                // Text formatting
                String userId = rs.getString(2);
                String creatorId = rs.getString(7);
                String assignee = SQLDCusers.getUsername(userId);
                String creator = SQLDCusers.getUsername(creatorId);

                currentTodo.put("assignee", SQLDCusers.getNameString(assignee));
                currentTodo.put("creator", SQLDCusers.getNameString(creator));

                todoList.add(currentTodo);
            }
            return todoList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
     * WGV-115
     * Deactivates done ToDos older than 30 days
     */
    public static void deactivateOldToDos() {
        executeQuery("UPDATE todo SET isActive=0 WHERE isDone=1 AND dateDue < DATE_ADD(CURDATE(), INTERVAL -30 DAY);");
    }

    /**
     * Get the wgId of a todo
     *
     * @param todoId The todoId of the todo
     * @return The wgId associated to the todo
     */
    public static String getWgIdOfTodo(String todoId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM todo WHERE uniqueID = " + Integer.valueOf(todoId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Return the number of open todos for a user for his current wg
     *
     * @param userId the userId of the user
     * @param wgId   The wgId of the current wg
     * @return The number of open todos
     */
    public static int getOpenTodosPerUser(String userId, String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT COUNT(uniqueID) FROM todo WHERE userId="
                    + Integer.valueOf(userId) + " AND isDone = 0 AND isActive = 1 AND wgId = " + Integer.valueOf(wgId)));

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Return the number of open todos for a wg
     *
     * @param wgId the wgId of the wg
     * @return The number of open todos
     */
    public static int getOpenTodosPerWg(String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT COUNT(uniqueID) FROM todo WHERE wgId="
                    + Integer.valueOf(wgId) + " AND isDone = 0 AND isActive = 1"));

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Sends SQL Query in order to count number of To-Do's for specific WG
     *
     * @param wgId The ID of the WG
     * @return Number of To-Do's for given WG, done or not
     */
    public static int countTodo(String wgId) {
        int numberofTodo;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wgId + ";");
            numberofTodo = rs.getInt(1);
            return numberofTodo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sends SQL Query in order to count the number of done To-Do's for specific WG
     *
     * @param wgId The ID of the WG
     * @return Number of done To-Do's
     */
    public static int countDone(String wgId) {
        int numberofDone;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wgId + " AND isDone=1;");
            numberofDone = rs.getInt(1);
            return numberofDone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sends SQL Query in order to count the number of done To-Do's assigned to specific User
     *
     * @param userId The ID of the user
     * @return Number of done To-Do's
     */
    public static int countDoneUser(String userId) {
        int numberofDone;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE userID=" + userId + " AND isDone=1;");
            numberofDone = rs.getInt(1);
            return numberofDone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sends SQL Query in order to count number of To-Do's for specific WG
     *
     * @param userId The ID of the user
     * @return Number of To-Do's for given user, done or not
     */
    public static int countTodoUser(String userId) {
        int numberofTodo;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE userId=" + userId + ";");
            numberofTodo = rs.getInt(1);
            return numberofTodo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}