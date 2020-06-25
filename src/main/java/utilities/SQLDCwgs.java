package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

/*
 Table structure:

        - uniqueID      (int)
        - name          (String)
        - accessKey     (String)
        - createdBy     (int)       (Foreign key to users.uniqueID)
 */

/**
 * Provides SQL accessor methods for everything that accesses the wgs table
 */
public class SQLDCwgs extends SQLDatabaseConnection {
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

    /**
     * Create a wg in the database
     *
     * @param name      the name of the wg
     * @param accessKey the access key of the wg
     * @return If the wg has been created successfully. If not, the user has to be informed!
     */
    public static boolean createWg(String name, String accessKey, String createdBy) {
        try {
            ResultSet rs = executeQuery(("INSERT INTO wgs (name, accessKey, createdBy)"
                    + "VALUES ('" + name + "', '" + accessKey + "', " + Integer.valueOf(createdBy) + ")"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return the wg ID for an access key
     *
     * @param accessKey the access key of the wg
     * @return the wg ID
     */
    public static String getWgId(String accessKey) {
        String wgId = "";

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID FROM wgs WHERE accessKey='" + accessKey + "'"));
            while (rs.next()) {
                wgId = rs.getString(1);
            }
            return wgId;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Get a list with all wg access keys
     *
     * @return The list with access keys
     */
    public static ArrayList<String> getAccessKeyList() {
        ArrayList<String> stringList = new ArrayList<>();

        try {
            ResultSet rs = executeQuery(("SELECT accessKey FROM wgs"));
            while (rs.next()) {
                stringList.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringList;
    }

    /**
     * Get the wg name by the wg Id
     *
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
