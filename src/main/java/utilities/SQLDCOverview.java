package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

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
     * Sends SQL Query to fetch first Name
     *
     * @param userId The ID of the User
     * @return First Name of User
     */
    public static String getFirstName(String userId) {
        String firstName = "";
        try {
            ResultSet rs = executeQuery("SELECT firstName FROM users WHERE uniqueID=" + Integer.valueOf(userId) + ";");
            while (rs.next()) {
                firstName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstName;
    }

    /**
     * Sends SQL Query to fetch Last Name
     *
     * @param userId The ID of the User
     * @return Last Name of User
     */
    public static String getLastName(String userId) {
        String lastName = "";
        try {
            ResultSet rs = executeQuery("SELECT lastName FROM users WHERE uniqueID=" + Integer.valueOf(userId) + ";");
            while (rs.next()) {
                lastName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastName;
    }

    /**
     * Concatenates First and Last Name
     *
     * @param userId The ID of the User
     * @return Full Name
     */
    public static String getFullName(String userId) {
        String firstName;
        String lastName;
        try {
            firstName = getFirstName(userId);
            lastName = getLastName(userId);
            return firstName + " " + lastName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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