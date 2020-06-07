package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        System.out.println(getUsers());
    }

    public static List<String> getUsers(){
        List<String> retList = new ArrayList<String>();

        ResultSet rs = executeQuery("SELECT username FROM users");

        try {
            while (rs.next()) {
                retList.add(rs.getString(1));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return retList;
    }


    /**
     * Executes a given sql query and returns the ResultSet
     * @param query The desired query
     * @return The ResultSet
     */
    private static ResultSet executeQuery(String query){
        ResultSet returnSet = null;

        try {
            Connection con = DriverManager.getConnection(
                    ("jdbc:mariadb://v220190910299696193.nicesrv.de:3306/wg_verwaltung?user=wg_admin&password=" + SecretsReader.getSqlPassword()));
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
