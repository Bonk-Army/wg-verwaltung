package utilities;

import java.sql.*;

import config.globalConfig;

/**
 * Provides logic to make callouts to SQL. Actual access is provided via child-class-functions for clearer structure
 */
public class SQLDatabaseConnection {
    /**
     * Executes a given sql query and returns the ResultSet
     *
     * @param query The desired query
     * @return The ResultSet
     */
    protected static ResultSet executeQuery(String query) {
        ResultSet returnSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String database = globalConfig.isTest() ? "wg_verwaltung_dev" : "wg_verwaltung";
            String password = System.getenv("SQL_PASSWORD");
            String server = System.getenv("SQL_SERVER");

            Connection con = DriverManager.getConnection(
                    ("jdbc:mariadb://" + server + ":3306/" + database + "?user=wg_admin&password=" + password));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            returnSet = rs;
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return returnSet;
    }
}
