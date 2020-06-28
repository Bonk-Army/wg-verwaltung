package utilities;

import models.User;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;

/*
 Table structure:

        - uniqueID          (int)
        - username          (String)
        - email             (String)
        - pwhash            (String)
        - pwsalt            (String)
        - verificationCode  (String)
        - rights            (Strings)
        - isVerified        (bool)
        - passwordResetKey  (String)
        - cookiePostfix     (String)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
        - firstName         (String)
        - lastName          (String)
        - registrationDate  (Date)
        - lastLogin         (Datetime)
        - cookieLifetime    (int)
        - lastPasswordLogin (Datetime)
 */

/**
 * Provides SQL accessor methods for everything that accesses the users table
 */
public class SQLDCusers extends SQLDatabaseConnection {
    /**
     * Create a user in the database
     *
     * @param username         The username of the user
     * @param email            The email address of the user (check this before inserting!)
     * @param pwhash           The hashed password
     * @param pwsalt           The salt that has been used for hashing
     * @param verificationCode The randomly generated code that the user needs to verify his email
     * @param firstName        The first name of the user
     * @param lastName         The last name of the user
     * @param cookiePostfix    The postfix of the session cookie
     * @param cookieLifetime   The lifetime of the session cookie in days
     * @return If the user has been created successful. If not, the user has to be informed!
     */
    public static boolean createUser(String username, String email, String pwhash, String pwsalt, String verificationCode, String firstName, String lastName, String cookiePostfix, int cookieLifetime) {
        try {
            Date registrationDate = DateFormatter.getCurrentDateTime();
            // Convert dates to java.sql.Timestamp to save them to SQL
            Timestamp registrationStamp = new Timestamp(registrationDate.getTime());
            // Save to SQL. We save the registration timestamp to registrationDate and lastPasswordLogin because that's the same thing here anyways
            ResultSet rs = executeQuery(("INSERT INTO users (username, email, pwhash, pwsalt, verificationCode, firstName, lastName, cookiePostfix, registrationDate, cookieLifetime, lastPasswordLogin)"
                    + "VALUES ('" + username + "', '" + email + "', '" + pwhash + "', '"
                    + pwsalt + "', '" + verificationCode + "', '" + firstName + "', '"
                    + lastName + "', '" + cookiePostfix + "', '" + registrationStamp + "', "
                    + cookieLifetime + ",  '" + registrationStamp + "')"));

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
            ResultSet rs = executeQuery("UPDATE users SET isVerified = true, verificationCode = '' WHERE username = '" + username + "'");
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
     * Set the new name for the given user
     *
     * @param userId    The userId of the user
     * @param firstName The new first name
     * @param lastName  The new last name
     * @return If it was successful
     */
    public static boolean setName(String userId, String firstName, String lastName) {
        try {
            executeQuery("UPDATE users SET firstName = '" + firstName + "', lastName = '" + lastName + "' WHERE uniqueID = " + Integer.valueOf(userId));

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
            ResultSet rs = executeQuery(("SELECT cookiePostfix FROM users WHERE username='" + username + "'"));

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
            executeQuery(("UPDATE users SET cookiePostfix='" + cookiePostfix + "' WHERE username = '" + username + "'"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Clear the wgId for the given user
     *
     * @param userId The userId of the user
     * @return If it was successful
     */
    public static boolean clearWg(String userId) {
        try {
            executeQuery(("UPDATE users SET wgID = " + null + " WHERE uniqueID = " + Integer.valueOf(userId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return all user data as a user object
     *
     * @param userId The userId of the user to get data for
     * @return The User object
     */
    public static User getAllUserData(String userId) {
        User user = new User();

        try {
            ResultSet userSet = executeQuery(("SELECT username ,firstName, lastName, wgId, email FROM users WHERE uniqueID = " + Integer.valueOf(userId)));

            while (userSet.next()) {
                user.setUserId(userId);
                user.setUsername(userSet.getString(1));
                user.setFirstName(userSet.getString(2));
                user.setLastName(userSet.getString(3));
                user.setWgId(String.valueOf(userSet.getInt(4)));
                user.setEmail(userSet.getString(5));
            }

            ResultSet wgSet = executeQuery(("SELECT name FROM wgs WHERE uniqueID = " + Integer.valueOf(user.getWgId())));

            while (wgSet.next()) {
                user.setWgName(wgSet.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Return a list of maps of which each contains the username and the nameString of a user for all users of the specified wg
     *
     * @param wgId The wgId of the wg
     * @return A list of maps of which each represents a user
     */
    public static List<Map<String, String>> getAllNameStringsForWg(String wgId) {
        List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();

        try {
            ResultSet rs = executeQuery(("SELECT username, firstName, lastName FROM users WHERE wgId="
                    + Integer.valueOf(wgId) + " ORDER BY firstName"));

            while (rs.next()) {
                Map<String, String> currentUser = new HashMap<String, String>();

                currentUser.put("username", rs.getString(1));

                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                currentUser.put("nameString", (firstName + " " + lastName.substring(0, 1)));

                nameList.add(currentUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nameList;
    }

    /**
     * Return a list of maps of which each contains the userId and the nameString of a user for all users of the specified wg
     *
     * @param wgId The wgId of the wg
     * @return A list of maps of which each represents a user
     */
    public static List<Map<String, String>> getAllNameStringsWithUserIdForWg(String wgId) {
        List<Map<String, String>> nameList = new ArrayList<Map<String, String>>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID, firstName, lastName FROM users WHERE wgId="
                    + Integer.valueOf(wgId) + " ORDER BY firstName"));

            while (rs.next()) {
                Map<String, String> currentUser = new HashMap<String, String>();

                currentUser.put("userId", String.valueOf(rs.getInt(1)));

                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                currentUser.put("nameString", (firstName + " " + lastName.substring(0, 1)));

                nameList.add(currentUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nameList;
    }

    /**
     * Returns a list of all the ids of every user in the given wg ordered by the first name of the user
     *
     * @param wgId The wgId of the wg to get the users for
     * @return The List of ids
     */
    public static List<String> getAllUserIdsOfWg(String wgId) {
        List<String> ids = new ArrayList<String>();

        try {
            ResultSet rs = executeQuery(("SELECT uniqueID FROM users WHERE wgID="
                    + Integer.valueOf(wgId) + " ORDER BY firstName"));

            while (rs.next()) {
                ids.add(String.valueOf(rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids;
    }

    /**
     * Set the wg ID of an user
     *
     * @param wgId   the new wg ID
     * @param userId the user ID who changes the wg
     * @return If the wg ID has been set successfully. If not, the user has to be informed!
     */
    public static boolean setWgId(String wgId, String userId) {
        try {
            executeQuery(("UPDATE users SET wgId=" + Integer.valueOf(wgId) + " WHERE uniqueID=" + Integer.valueOf(userId)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Set the passed right for the specified user
     *
     * @param userID The userId of the user
     * @param right  The right to be set for the user
     * @return If it was successful
     */
    public static boolean setUserRights(String userID, String right) {
        try {
            executeQuery(("UPDATE users SET rights='" + right + "' WHERE uniqueID=" + Integer.valueOf(userID)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return a list with all usernames of the users in the specified WG
     *
     * @param wgId The wgId of the WG
     * @return The list of usernames
     */
    public static List<String> getAllUsersOfWG(String wgId) {
        List<String> users = new ArrayList<String>();

        try {
            ResultSet rs = executeQuery(("SELECT username FROM users WHERE wgId=" + Integer.valueOf(wgId) + " ORDER BY firstName ASC"));

            while (rs.next()) {
                users.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Get the wgId for the specified user
     *
     * @param userId The userId of the user
     * @return The wgId as a String
     */
    public static String getWgIdByUser(String userId) {
        String wgId = "";

        try {
            ResultSet rs = executeQuery("SELECT wgId FROM users WHERE uniqueID=" + Integer.valueOf(userId));

            while (rs.next()) {
                wgId = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wgId;
    }

    /**
     * Return the first name of the user by his username
     *
     * @param username The username of the user
     * @return The first name of the user as a String
     */
    public static String getFirstName(String username) {
        String firstName = "";
        try {
            ResultSet rs = executeQuery("SELECT firstName FROM users WHERE username='" + username + "'");

            while (rs.next()) {
                firstName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return firstName;
    }

    /**
     * Return the first name of the user by his username
     *
     * @param username The username of the user
     * @return The first name of the user as a String
     */
    public static String getLastName(String username) {
        String lastName = "";
        try {
            ResultSet rs = executeQuery("SELECT lastName FROM users WHERE username='" + username + "'");

            while (rs.next()) {
                lastName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastName;
    }

    /**
     * Concatenates First name and the first letter of the last name
     *
     * @param username The username of the user
     * @return The name string
     */
    public static String getNameString(String username) {
        String firstName = "";
        String lastName = "";
        try {
            ResultSet rs = executeQuery(("SELECT firstName, lastName FROM users WHERE username='" + username + "'"));

            while (rs.next()) {
                firstName = rs.getString(1);
                lastName = rs.getString(2);
            }

            return firstName + " " + lastName.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Concatenates First name and the first letter of the last name
     *
     * @param userId The userId of the user
     * @return The name string
     */
    public static String getNameStringById(String userId) {
        String firstName = "";
        String lastName = "";
        try {
            ResultSet rs = executeQuery(("SELECT firstName, lastName FROM users WHERE uniqueID=" + Integer.valueOf(userId)));

            while (rs.next()) {
                firstName = rs.getString(1);
                lastName = rs.getString(2);
            }

            return firstName + " " + lastName.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Concatenates First and Last Name
     *
     * @param userId The ID of the User
     * @return Full Name
     */
    public static String getFullName(String userId) {
        String firstName = "";
        String lastName = "";
        try {

            ResultSet rs = executeQuery(("SELECT firstName, lastName FROM users WHERE uniqueID=" + Integer.valueOf(userId)));

            while (rs.next()) {
                firstName = rs.getString(1);
                lastName = rs.getString(2);
            }
            return firstName + " " + lastName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Return the wgId of the given user
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public static String getWgIdFromUserId(String userId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM users WHERE uniqueId=" + Integer.valueOf(userId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Set the last login field to the current time
     *
     * @param userId The userId of the user that logged in recently
     * @return If it was successful
     */
    public static boolean setLastLogin(String userId) {
        try {
            Date now = DateFormatter.getCurrentDateTime();
            Timestamp nowStamp = new Timestamp(now.getTime());
            executeQuery(("UPDATE users SET lastLogin='" + nowStamp + "' WHERE uniqueID = " + Integer.valueOf(userId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Set the last password login field to the current time
     *
     * @param username The username of the user that logged in via password recently
     * @return If it was successful
     */
    public static boolean setLastPasswordLogin(String username) {
        try {
            Date now = DateFormatter.getCurrentDateTime();
            Timestamp nowStamp = new Timestamp(now.getTime());
            executeQuery(("UPDATE users SET lastPasswordLogin='" + nowStamp + "' WHERE username = '" + username + "'"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the email address of the specified user
     *
     * @param userId The userId of the user
     * @return The email address
     */
    public static String getEmailAddressForUser(String userId) {
        try {
            ResultSet rs = executeQuery(("SELECT email FROM users WHERE uniqueID=" + Integer.valueOf(userId)));

            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Get the last login time for the given user as a formatted string
     *
     * @param userId The userId of the user
     * @return The date as a formatted string
     */
    public static String getLastLogin(String userId) {
        try {
            ResultSet rs = executeQuery(("SELECT lastLogin FROM users WHERE uniqueID=" + Integer.valueOf(userId)));

            while (rs.next()) {
                Date lastLoginDate = rs.getDate(1);

                return DateFormatter.dateTimeToString(lastLoginDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Set the session cookie lifetime for the given user
     *
     * @param username The username of the user
     * @param lifetime The lifetime in days
     * @return If it was successful
     */
    public static boolean setCookieLifetime(String username, int lifetime) {
        try {
            executeQuery(("UPDATE users SET cookieLifetime = " + lifetime + " WHERE username = '" + username + "'"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Check if the session cookie of the specified user is still valid
     *
     * @param userId The userId of the user
     * @return If the cookie is still valid
     */
    public static boolean isSessionCookieStillValid(String userId) {
        try {
            ResultSet rs = executeQuery(("SELECT cookieLifetime, lastPasswordLogin FROM users WHERE uniqueID = " + Integer.valueOf(userId)));

            while (rs.next()) {
                Date now = DateFormatter.getCurrentDateTime();
                Date lastPasswordLogin = rs.getDate(2);
                int cookieLifetime = rs.getInt(1);

                Calendar c = Calendar.getInstance();
                c.setTime(lastPasswordLogin);
                c.add(Calendar.DATE, cookieLifetime);
                Date cookieLifetimeEnd = c.getTime();

                return !now.after(cookieLifetimeEnd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
