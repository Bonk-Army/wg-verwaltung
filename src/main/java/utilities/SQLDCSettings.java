package utilities;

import java.sql.ResultSet;

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
            if (RegexHelper.checkUsername(name)) {
                ResultSet rs = executeQuery(("INSERT INTO wgs (name, accessKey)"
                        + "VALUES ('" + name + "', '" + accessKey + "')"));
                return true;
            }
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
            if (RegexHelper.checkUsername(accessKey)) {
                ResultSet rs = executeQuery(("SELECT uniqueID FROM wgs WHERE accessKey='" + accessKey + "'"));
                while (rs.next()) {
                    wgId = rs.getString(1);
                }
                return wgId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean setWgId(String wgId, String userId) {
        try {
            executeQuery(("UPDATE users SET wgId='" + wgId + "' WHERE userId=" + userId + "'"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
