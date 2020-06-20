package utilities;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides SQL accessor methods for the login page
 */
public class SQLDCLogin extends SQLDatabaseConnection {
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
    public static boolean createUser(String username, String email, String pwhash, String pwsalt, String verificationCode, String firstName, String lastName, String cookiePostfix) {
        try {
            Date registrationDate = new Date();
            // Convert dates to java.sql.Timestamp to save them to SQL
            Timestamp registrationStamp = new Timestamp(registrationDate.getTime());
            ResultSet rs = executeQuery(("INSERT INTO users (username, email, pwhash, pwsalt, verificationCode, firstName, lastName, cookiePostfix, registrationDate)"
                    + "VALUES ('" + username + "', '" + email + "', '" + pwhash + "', '"
                    + pwsalt + "', '" + verificationCode + "', '" + firstName + "', '"
                    + lastName + "', '" + cookiePostfix + "', '" + registrationStamp + "')"));

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

    /**
     * Get the username of a user by his email address
     *
     * @param email The email address
     * @return The username
     */
    public static String getUsernameByEmail(String email) {
        String username = "";

        ResultSet rs = executeQuery("SELECT username FROM users WHERE email='" + email + "'");

        try {
            while (rs.next()) {
                username = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }

    /**
     * Get the verification code for the given user
     *
     * @param username The user
     * @return The verification code
     */
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
     * Save the key to reset the password to SQL
     *
     * @param email The users email address
     * @param key   The key that the user needs to reset the password
     * @return If it  was successful
     */
    public static boolean setPasswordKey(String email, String key) {
        try {
            executeQuery("UPDATE users SET passwordResetKey='" + key + "' WHERE email='" + email + "'");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the saved password reset key for a user
     *
     * @param username The user to get the key from
     * @return The key as a String
     */
    public static String getPasswordKey(String username) {
        String key = "";

        try {
            ResultSet rs = executeQuery("SELECT passwordResetKey FROM users WHERE username = '" + username + "'");

            while (rs.next()) {
                key = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return key;
    }

    /**
     * Set the new password for a user
     *
     * @param username The username that the password should be set for
     * @param pwhash   The new password hash
     * @return If it was successful
     */
    public static boolean setPassword(String username, String pwhash) {
        try {
            executeQuery("UPDATE users SET pwhash='" + pwhash + "', passwordResetKey=NULL WHERE username='" + username + "'");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return the cookie postfix required for the session identifier for a specific user
     *
     * @param username The username of the user
     * @return The cookie postfix
     */
    public static String getCookiePostfix(String username) {
        String postfix = "";
        try {
            ResultSet rs = executeQuery("SELECT cookiePostfix FROM users WHERE username='" + username + "'");

            while (rs.next()) {
                postfix = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postfix;
    }

    /**
     * Set the cookie postfix parameter for the given user
     *
     * @param username      The username of the user
     * @param cookiePostfix The new cookie postfix
     * @return If it was successful
     */
    public static boolean setCookiePostfix(String username, String cookiePostfix) {
        try {
            executeQuery("UPDATE users SET cookiePostfix='" + cookiePostfix + "' WHERE username = '" + username + "'");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
