package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            Connection con = DriverManager.getConnection(
                    ("jdbc:mariadb://v220190910299696193.nicesrv.de:3306/" + database + "?user=wg_admin&password=" + System.getenv("SQL_PASSWORD")));
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
