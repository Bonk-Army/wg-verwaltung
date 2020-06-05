package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        String connectionURL =
                "jdbc:sftp://v220190910299696193.nicesrv.de:3306;"
                        + "database=wg-verwaltung;"
                        + "user=wg_admin;"
                        + "password=;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try(Connection connection = DriverManager.getConnection(connectionURL);){
            System.out.println("Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
