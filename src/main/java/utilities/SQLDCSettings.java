package utilities;

import java.sql.ResultSet;

public class SQLDCSettings extends SQLDatabaseConnection {
    /**
     * Create a wg in the database
     *
     * @param name      the name of the wg
     * @param accessKey the access key of the wg
     * @return If the to-do has been created successfully. If not, the user has to be informed!
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
        } catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }


}
