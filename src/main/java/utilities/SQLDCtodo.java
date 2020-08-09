package utilities;

import models.TodoEntry;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

/*
 Table structure:

        - uniqueID          (int)       (Primary key)
        - isActive          (bool)
        - task              (String)
        - assignedId        (int)       (Foreign key to users.uniqueID)
        - dateCreated       (Datetime)
        - dateDue           (Datetime)
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
     * @param assignedId  The ID of the user that is assigned to this task
     * @param wgId        The ID of the WG
     * @param dateDue     The date til the task should be done
     * @param createdById The id of the user that created the todo
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String assignedId, String wgId, Date dateDue, String createdById) {
        try {
            Date dateCreated = DateFormatter.getCurrentDateTime();
            // Convert dates to java.sql.Timestamp to save them to SQL
            Timestamp createdStamp = new Timestamp(dateCreated.getTime());
            Timestamp dueStamp = new Timestamp(dateDue.getTime());

            ResultSet rs = executeQuery(("INSERT INTO todo (task, assignedId, wgId, dateCreated, dateDue, isDone, createdBy, isActive) "
                    + "VALUES ('" + task + "', " + Integer.valueOf(assignedId) + ", " + Integer.valueOf(wgId) + ", '"
                    + createdStamp + "', '" + dueStamp + "', " + false + ", " + Integer.valueOf(createdById) + ", 1)"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return all active To-Dos for a wg.
     * The method first fetches the data from sql and then parses it into a format that can be read directly in the
     * frontend. It returns a list of todos where each todo is a map with the respective key-value pairs.
     * This method also "looks" at the data and gives the required color info into the maps so we don' need to
     * have calculation for this in the frontend.
     *
     * @param wgId the ID of the wg
     * @return A list of maps of which each represents a todo
     */
    public static List<Map<String, String>> getAllActiveTodos(String wgId) {
        deactivateOldToDos();
        List<Map<String, String>> todoList = new ArrayList<Map<String, String>>();
        try {
            // Get all required info for each todo that is active and is either not done or has been due in the last seven days.
            // Ordered by their status and todos of the same status are ordered by their due date
            ResultSet rs = executeQuery(("SELECT task, assignedId, dateCreated, dateDue, isDone, isActive, createdBy, uniqueID FROM todo WHERE wgId = "
                    + Integer.valueOf(wgId) + " AND isActive = 1 AND (isDone = 0 OR DATEDIFF(CURRENT_TIMESTAMP, dateDue) <= 7) ORDER BY isDone, dateDue ASC"));
            while (rs.next()) {
                Map<String, String> currentTodo = new HashMap<String, String>();
                currentTodo.put("task", rs.getString(1));
                currentTodo.put("isDone", String.valueOf(rs.getBoolean(5)));
                currentTodo.put("todoId", rs.getString(8));

                // Parameters for better visualization of the status of every todo

                // Dates for colors
                Date dateDue = rs.getTimestamp(4);
                Date dateCreated = rs.getTimestamp(3);
                Date currentDate = DateFormatter.getCurrentDateTime();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 3);
                Date threeDaysDate = c.getTime();

                currentTodo.put("dateDue", DateFormatter.dateTimeMinutesToString(dateDue));
                currentTodo.put("dateCreated", DateFormatter.dateTimeMinutesToString(dateCreated));

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
                String assignedId = rs.getString(2);
                String creatorId = rs.getString(7);
                String assignee = SQLDCusers.getUsername(assignedId);
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
    public static boolean deactivateOldToDos() {
        try {
            executeQuery("UPDATE todo SET isActive=0 WHERE isDone=1 AND dateDue < DATE_ADD(CURDATE(), INTERVAL -30 DAY);");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the wgId of a todo
     *
     * @param todoId The todoId of the todo
     * @return The wgId associated to the todo
     */
    public static String getWgIdOfTodo(String todoId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM todo WHERE uniqueID=" + Integer.valueOf(todoId)));

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
     * @param assignedId the assignedId of the user
     * @param wgId       The wgId of the current wg
     * @return The number of open todos
     */
    public static int getOpenTodosPerUser(String assignedId, String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT COUNT(uniqueID) FROM todo WHERE assignedId="
                    + Integer.valueOf(assignedId) + " AND isDone = 0 AND isActive = 1 AND wgId = " + Integer.valueOf(wgId)));

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
     * Get all active todos where the given user is assigned.
     * The method first fetches the data from sql and then parses it into a format that can be read directly in the
     * frontend. It returns a list of todos where each todo is a map with the respective key-value pairs.
     * This method also "looks" at the data and gives the required color info into the maps so we don' need to
     * have calculation for this in the frontend.
     *
     * @param assignedId The assignedId of the user
     * @return The List of todos
     */
    public static List<Map<String, String>> getAllActiveTodosForUser(String assignedId, String wgId) {
        deactivateOldToDos();
        List<Map<String, String>> todoList = new ArrayList<Map<String, String>>();
        try {
            // Gets the required info for each todo that is active and is either not done or has been due in the last seven days.
            // Ordered by the status and the todos of the same status are then ordered by their due date
            ResultSet rs = executeQuery(("SELECT task, assignedId, dateCreated, dateDue, isDone, isActive, createdBy, uniqueID FROM todo WHERE assignedId = "
                    + Integer.valueOf(assignedId) + " AND wgId = " + Integer.valueOf(wgId) + " AND isActive = 1 "
                    + " AND (isDone = 0 OR DATEDIFF(CURRENT_TIMESTAMP, dateDue) <= 7) ORDER BY isDone, dateDue ASC"));
            while (rs.next()) {
                Map<String, String> currentTodo = new HashMap<String, String>();
                currentTodo.put("task", rs.getString(1));
                currentTodo.put("isDone", String.valueOf(rs.getBoolean(5)));
                currentTodo.put("todoId", rs.getString(8));

                // Parameters for better visualization of the status of every todo

                // Dates for colors
                Date dateDue = rs.getTimestamp(4);
                Date dateCreated = rs.getTimestamp(3);
                Date currentDate = DateFormatter.getCurrentDateTime();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 3);
                Date threeDaysDate = c.getTime();

                currentTodo.put("dateDue", DateFormatter.dateTimeMinutesToString(dateDue));
                currentTodo.put("dateCreated", DateFormatter.dateTimeMinutesToString(dateCreated));

                boolean isDone = rs.getBoolean(5);
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
                String creatorId = rs.getString(7);
                String creator = SQLDCusers.getUsername(creatorId);

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
     * Get the <b>number</b> of open todos for every user of the wg alongside their name string
     *
     * @param wgId The wgId of the wg to fetch the users for
     * @return A map with the name as key and the number of open todos as value
     */
    public static Map<String, Integer> getOpenTodosPerUserOfWg(String wgId) {
        Map<String, Integer> openTodosMap = new HashMap<String, Integer>();

        try {
            // Returns the count of open todos and required user info for each user of the wg individually
            ResultSet rs = executeQuery(("SELECT COUNT(todo.uniqueID), users.firstName, users.lastName FROM todo "
                    + "LEFT OUTER JOIN users ON users.uniqueID = todo.assignedId WHERE todo.wgId = " + Integer.valueOf(wgId)
                    + " AND todo.isActive = 1 AND todo.isDone = 0 GROUP BY todo.assignedId"));

            while (rs.next()) {
                String nameString = rs.getString(2) + " " + rs.getString(3).substring(0, 1);
                int openTodos = rs.getInt(1);

                openTodosMap.put(nameString, openTodos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return openTodosMap;
    }

    /**
     * Get todos on wg-level for use in the mobile app
     *
     * @param wgId The wgId of the wg to fetch the todos for
     * @return A List of TodoEntry Objects
     */
    public static List<TodoEntry> getWgEntriesForMobileApp(String wgId) {
        deactivateOldToDos();
        List<TodoEntry> todoList = new ArrayList<TodoEntry>();
        try {
            // Get all required info for each todo that is active and is either not done or has been due in the last seven days.
            // Ordered by their status and todos of the same status are ordered by their due date
            ResultSet rs = executeQuery(("SELECT task, assignedId, dateCreated, dateDue, isDone, isActive, createdBy, uniqueID FROM todo WHERE wgId = "
                    + Integer.valueOf(wgId) + " AND isActive = 1 AND (isDone = 0 OR DATEDIFF(CURRENT_TIMESTAMP, dateDue) <= 7) ORDER BY isDone, dateDue ASC"));
            while (rs.next()) {
                String task = rs.getString(1);
                boolean isDone = rs.getBoolean(5);
                String entryId = String.valueOf(rs.getInt(8));

                // Parameters for better visualization of the status of every todo

                // Dates for colors
                Date dateDue = rs.getTimestamp(4);
                Date dateCreated = rs.getTimestamp(3);
                Date currentDate = DateFormatter.getCurrentDateTime();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 3);
                Date threeDaysDate = c.getTime();

                String dueDateString = DateFormatter.dateTimeMinutesToString(dateDue);
                String createdDateString = DateFormatter.dateTimeMinutesToString(dateCreated);

                // Text formatting
                String assignedId = rs.getString(2);
                String creatorId = rs.getString(7);
                String assignee = SQLDCusers.getNameStringById(assignedId);
                String creator = SQLDCusers.getNameStringById(creatorId);

                todoList.add(new TodoEntry(task, dueDateString, createdDateString, assignee, creator, isDone, entryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todoList;
    }
}
