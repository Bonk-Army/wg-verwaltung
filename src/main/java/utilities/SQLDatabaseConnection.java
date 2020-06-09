package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to save and fetch data to and from SQL
 */
public class SQLDatabaseConnection {
    /**
     * Create a user in the database
     *
     * @param username         The username of the user
     * @param email            The email address of the user (check this before inserting!)
     * @param pwhash           The hashed password
     * @param pwsalt           The salt that has been used for hashing
     * @param verificationCode The randomly generated code that the user needs to verify his email
     * @return If the user has been created successful. If not, the user has to be informed!
     */
    public static boolean createUser(String username, String email, String pwhash, String pwsalt, String verificationCode) {
        try {
            ResultSet rs = executeQuery(("INSERT INTO users (username, email, pwhash, pwsalt, verificationCode)"
                    + "VALUES ('" + username + "', '" + email + "', '" + pwhash + "', '" + pwsalt + "', '" + verificationCode + "')"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the password hash for a given username
     *
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }

    /**
     * Get the salt required for hashing the password of the given username
     *
     * @param username The username the salt is returned for
     * @return The salt as a String
     */
    public static String getPasswordSalt(String username) {
        String salt = "";

        ResultSet rs = executeQuery("SELECT pwsalt FROM users WHERE username='" + username + "'");

        try {
            while (rs.next()) {
                salt = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return salt;
    }

    /**
     * Set the "isVerified" field for the specified user to true and delete verification code to de-validate it.
     *
     * @param username The user that has been verified
     * @return if the value has been changed successfully. If not, the process has to be tried again.
     */
    public static boolean verifyUser(String username) {
        try {
            ResultSet rs = executeQuery("UPDATE users SET isVerified = true WHERE username = '" + username + "'");
            ResultSet rs2 = executeQuery("UPDATE users SET verificationCode = '' WHERE username = '" + username + "'");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the unique user id for the requested user
     *
     * @param username The user that the id has to be returned for
     * @return The id as a String
     */
    public static String getUserId(String username) {
        String id = "";

        ResultSet rs = executeQuery("SELECT uniqueID FROM users WHERE username='" + username + "'");

        try {
            while (rs.next()) {
                id = String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * Get the username for the requested unique user id
     *
     * @param userID The unique id
     * @return The username as a String
     */
    public static String getUsername(String userID) {
        String name = "";

        ResultSet rs = executeQuery("SELECT username FROM users WHERE uniqueID=" + Integer.valueOf(userID));

        try {
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String getUsernameByEmail(String email) {
        String username = "";

        ResultSet rs = executeQuery("SELECT username FROM users WHERE email=" + email);

        try {
            while (rs.next()) {
                username = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }

    public static String getUserVerificationCode(String username) {
        String code = "";

        ResultSet rs = executeQuery("SELECT verificationCode FROM users WHERE username='" + username + "'");

        try {
            while (rs.next()) {
                code = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }

    /**
     * Get all user names (used to check if name is already used)
     *
     * @return A List of all usernames
     */
    public static List<String> getAllUserNames() {
        List<String> retList = new ArrayList<String>();

        ResultSet rs = executeQuery("SELECT username FROM users");

        try {
            while (rs.next()) {
                retList.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retList;
    }

    /**
     * Get a list of all email addresses (used to check for duplicate email)
     *
     * @return A list of all emails
     */
    public static List<String> getAllEmails() {
        List<String> retList = new ArrayList<String>();

        ResultSet rs = executeQuery("SELECT email FROM users");

        try {
            while (rs.next()) {
                retList.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retList;
    }


    /**
     * Executes a given sql query and returns the ResultSet
     *
     * @param query The desired query
     * @return The ResultSet
     */
    private static ResultSet executeQuery(String query) {
        ResultSet returnSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

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

    public static boolean setPasswordKey(String email, String key) {
        try {
            executeQuery("UPDATE users SET passwordResetKey='" + key + "' WHERE email='" + email + "'");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean setPassword(String username, String key, String pwhash) {
        try {
            executeQuery("UPDATE users SET pwhash='" + pwhash + "', passwordResetKey=NULL WHERE username='" + username + "'");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
