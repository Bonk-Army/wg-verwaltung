package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Provides SQL accessor methods for the overview page
 */
public class SQLDCOverview extends SQLDatabaseConnection {
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

    /**
     * Return the wg name of the wg of the passed user
     *
     * @param userId The user id of the user
     * @return The wg name
     */
    public static String getWgName(String userId) {
        String wgName = "";

        try {
            ResultSet forWGID = executeQuery("SELECT wgID FROM users WHERE uniqueID=" + Integer.valueOf(userId));
            int wgId = 0;
            while (forWGID.next()) {
                wgId = forWGID.getInt(1);
            }

            ResultSet forWgName = executeQuery("SELECT name FROM wgs WHERE uniqueID=" + wgId);
            while (forWgName.next()) {
                wgName = forWgName.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wgName;
    }
}