package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabaseConnection {
    public static void main(String[] args) {
        System.out.println(System.getenv("SQL_PASSWORD"));
        System.out.println(validateUser("patrick2"));
    }

    /**
     * Create a user in the database
     * @param username The username of the user
     * @param email The email address of the user (check this before inserting!)
     * @param pwhash The hashed password
     * @param pwsalt The salt that has been used for hashing
     * @param verificationCode The randomly generated code that the user needs to verify his email
     * @return If the user has been created successful. If not, the user has to be informed!
     */
    public static boolean createUser(String username, String email, String pwhash, String pwsalt, String verificationCode) {
        try{
            ResultSet rs = executeQuery(("INSERT INTO users (username, email, pwhash, pwsalt, verificationCode)"
            + "VALUES ('" + username + "', '" + email + "', '" + pwhash + "', '" + pwsalt + "', '" + verificationCode + "')"));

            return true;
        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the password hash for a given username
     * @param username The username that the hash is returned for
     * @return The hash as String
     */
    public static String getPasswordHash(String username) {
        String hash = "";

        ResultSet rs = executeQuery("SELECT pwhash FROM users WHERE username='" + username + "'");

        try {
            while (rs.next()) {
                hash = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hash;
    }

    /**
     * Get the salt required for hashing the password of the given username
     * @param username The username the salt is returned for
     * @return The salt as a String
     */
    public static String getPasswordSalt(String username){
        String salt = "";

        ResultSet rs = executeQuery("SELECT pwsalt FROM users WHERE username='" + username + "'");

        try {
            while (rs.next()) {
                salt = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salt;
    }

    public static boolean validateUser(String username){
        try{
            ResultSet rs = executeQuery("UPDATE users SET isVerified = true WHERE username = '" + username + "'");
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get all user names (used to check if name is already used
     * @return A List of all usernames
     */
    public static List<String> getAllUserNames() {
        List<String> retList = new ArrayList<String>();

        ResultSet rs = executeQuery("SELECT username FROM users");

        try {
            while (rs.next()) {
                retList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retList;
    }


    /**
     * Executes a given sql query and returns the ResultSet
     * @param query The desired query
     * @return The ResultSet
     */
    private static ResultSet executeQuery(String query) {
        ResultSet returnSet = null;

        try {
            Connection con = DriverManager.getConnection(
                    ("jdbc:mariadb://v220190910299696193.nicesrv.de:3306/wg_verwaltung?user=wg_admin&password=" + System.getenv("SQL_PASSWORD")));
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
