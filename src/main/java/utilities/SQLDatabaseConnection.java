package utilities;

import java.sql.*;

import com.jcraft.jsch.*;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    ("jdbc:mariadb://v220190910299696193.nicesrv.de:3306/wg_verwaltung?user=wg_admin&password=" + SecretsReader.getSqlPassword()));
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next())
                System.out.println(rs.getString(1));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
