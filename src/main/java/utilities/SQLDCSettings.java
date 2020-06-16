package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLDCSettings extends SQLDatabaseConnection {
    /**
     * Create a wg in the database
     *
     * @param name      the name of the wg
     * @param accessKey the access key of the wg
     * @return If the wg has been created successfully. If not, the user has to be informed!
     */
    public static boolean createWg(String name, String accessKey) {
        try {
            ResultSet rs = executeQuery(("INSERT INTO wgs (name, accessKey)"
                    + "VALUES ('" + name + "', '" + accessKey + "')"));
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
     * Set the wg ID of an user
     *
     * @param wgId   the new wg ID
     * @param userId the user ID who changes the wg
     * @return If the wg ID has been set successfully. If not, the user has to be informed!
     */
    public static boolean setWgId(String wgId, String userId) {
        try {
            executeQuery(("UPDATE users SET wgId=" + Integer.valueOf(wgId) + " WHERE uniqueID=" + Integer.valueOf(userId)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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

}
