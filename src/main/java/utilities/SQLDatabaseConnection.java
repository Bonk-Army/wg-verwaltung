package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import com.jcraft.jsch.*;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        String connectionURL =
                "jdbc:sqlserver://v220190910299696193.nicesrv.de:22;"
                        + "database=wg-verwaltung;"
                        + "user=root;"
                        + "password=xxxxxxxxxxxxx;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionURL);) {
            System.out.println("Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
            JSch jsch = new JSch();
            String user = "wg_admin";
            String host = "v220190910299696193.nicesrv.de";
            int port = 3306;
            Session session = jsch.getSession(user, host, port);
            session.connect();
            Channel channel=session.openChannel("sftp");
            channel.connect();
            ChannelSftp c=(ChannelSftp)channel;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
