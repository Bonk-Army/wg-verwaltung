package utilities;

import java.sql.ResultSet;

/**
 * Provides SQL accessor methods that are used in many different places
 */
public class SQLDCUtility extends SQLDatabaseConnection {
    /**
     * Return the first name of the user by his username
     *
     * @param username The username of the user
     * @return The first name of the user as a String
     */
    public static String getFirstName(String username) {
        String firstName = "";
        try {
            ResultSet rs = executeQuery("SELECT firstName FROM users WHERE username='" + username + "'");

            while (rs.next()) {
                firstName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return firstName;
    }

    /**
     * Return the first name of the user by his username
     *
     * @param username The username of the user
     * @return The first name of the user as a String
     */
    public static String getLastName(String username) {
        String lastName = "";
        try {
            ResultSet rs = executeQuery("SELECT lastName FROM users WHERE username='" + username + "'");

            while (rs.next()) {
                lastName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastName;
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
            firstName = getFirstName(username);
            lastName = getLastName(username);
            return firstName + " " + lastName.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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
            firstName = SQLDCUtility.getFirstName(userId);
            lastName = SQLDCUtility.getLastName(userId);
            return firstName + " " + lastName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Return the wgId of the given user
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public static String getWgIdFromUserId(String userId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM users WHERE uniqueId=" + Integer.valueOf(userId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Get the wg name by the wg Id
     * @param wgId The wgId of the wg
     * @return The name of the wg
     */
    public static String getWgNameFromWgId(String wgId) {
        try {
            ResultSet rs = executeQuery(("SELECT name FROM wgs WHERE uniqueID=" + Integer.valueOf(wgId)));

            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
