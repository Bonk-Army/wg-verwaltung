package beans;

import utilities.*;
import java.util.List;

/**
 * Bean that handles all backend logic and database callouts required for user login and registration.
 */
public class LoginBean {
    private ErrorCodes status;

    public LoginBean() {
    }

    /*
      /$$$$$$                                 /$$             /$$
     /$$__  $$                               | $$            | $$
    | $$  \__/  /$$$$$$   /$$$$$$  /$$    /$$| $$  /$$$$$$  /$$$$$$
    |  $$$$$$  /$$__  $$ /$$__  $$|  $$  /$$/| $$ /$$__  $$|_  $$_/
     \____  $$| $$$$$$$$| $$  \__/ \  $$/$$/ | $$| $$$$$$$$  | $$
     /$$  \ $$| $$_____/| $$        \  $$$/  | $$| $$_____/  | $$ /$$
    |  $$$$$$/|  $$$$$$$| $$         \  $/   | $$|  $$$$$$$  |  $$$$/
     \______/  \_______/|__/          \_/    |__/ \_______/   \___/
     */
    // Methods used by servlets

    /**
     * Called by LoginServlet upon login action from user. Returns user id in case of successfull login, otherwise
     * returns an empty String
     *
     * @param username The entered username
     * @param password The entered password
     * @return The status of the request
     */
    public ErrorCodes login(String username, String password) {
        if (!RegexHelper.checkString(username)) {
            this.status = ErrorCodes.WRONGUNAME;
            return ErrorCodes.WRONGUNAME;
        }

        String salt = SQLDCLogin.getPasswordSalt(username);
        String hash = SQLDCLogin.getPasswordHash(username);
        String cookiePostfix = new RandomStringGenerator(21).nextString();

        if (!salt.isEmpty() && !hash.isEmpty()) {
            String newHash = PasswordHasher.hashPassword(password, salt);

            //Login was either successful or one of the entered params was wrong
            if (newHash.equals(hash)) {
                if (SQLDCLogin.setCookiePostfix(username, cookiePostfix)) {
                    this.status = ErrorCodes.SUCCESS;
                    return ErrorCodes.SUCCESS;
                } else {
                    this.status = ErrorCodes.FAILURE;
                    return ErrorCodes.FAILURE;
                }
            } else {
                this.status = ErrorCodes.WRONGENTRY;
                return ErrorCodes.WRONGENTRY;
            }
        }

        //Something failed server-side, return FAILURE
        this.status = ErrorCodes.FAILURE;
        return ErrorCodes.FAILURE;
    }

    /**
     * Called by LoginServlet upon register action from user. Returns the new user id in case the registration is
     * successful, otherwise returns an empty String
     *
     * @param username The entered username (must not exist yet)
     * @param password The entered password
     * @param email    The entered email (must not exist yet)
     * @return The status of the request
     */
    public ErrorCodes register(String username, String password, String email, String firstName, String lastName) {
        //Generate random salt
        String salt = PasswordHasher.generateSalt();

        //Calculate password hash
        String hash = PasswordHasher.hashPassword(password, salt);

        //Call SQL to ask if username / email is unique. If unique, it continues registration process, else it stops
        if (!RegexHelper.checkString(username) || !RegexHelper.checkString(firstName) || !RegexHelper.checkString(lastName) || !RegexHelper.checkEmail(email)) {
            return ErrorCodes.WRONGENTRY;
        } else {
            if (isUsernameUnique(username) && isEmailUnique(email)) {
                //Create new user. Generate random, 10-digit verification code for email verification.
                String verificationCode = new RandomStringGenerator(10).nextString();
                String cookiePostfix = new RandomStringGenerator(21).nextString();

                // If the user creation was successful, send an email and continue registration
                if (SQLDCLogin.createUser(username, email, hash, new String(salt), verificationCode, firstName, lastName, cookiePostfix)) {
                    // Now send an email to the user with the verification link
                    String verifyLink = "verify?uname=" + username + "&key=" + verificationCode;
                    String fullName = firstName + " " + lastName;
                    MailSender.sendVerificationMail(email, fullName, verifyLink);

                    // And return success
                    return ErrorCodes.SUCCESS;
                }
                // If something failed server-side, return FAILURE
                return ErrorCodes.FAILURE;
            } else {
                //If either already exists, WRONGENTRY
                return ErrorCodes.WRONGENTRY;
            }
        }
    }

    /**
     * Set a user's status to verified
     *
     * @param username         The username of the user to be verified
     * @param verificationCode The verification code the user 'entered'
     * @return The status of the verification
     */
    public ErrorCodes verifyUser(String username, String verificationCode) {
        if (!RegexHelper.checkString(username) || !RegexHelper.checkString(verificationCode)) {
            return ErrorCodes.WRONGENTRY;
        } else {
            String savedVerificationCode = SQLDCLogin.getUserVerificationCode(username);

            // If the entered verification code matches the saved one, verify the user
            if (verificationCode.equals(savedVerificationCode)) {
                SQLDCLogin.verifyUser(username);

                return ErrorCodes.SUCCESS;
            }
            return ErrorCodes.FAILURE;
        }
    }

    /**
     * Send an email with a link to reset their password to the user
     *
     * @param email The email address that was entered by the user
     * @return If the email has been sent successfully
     */
    public ErrorCodes sendPasswordResetLink(String email) {
        if (RegexHelper.checkEmail(email)) {
            String randomKey = new RandomStringGenerator(30).nextString();
            if (SQLDCLogin.setPasswordKey(email, randomKey)) {
                String username = SQLDCLogin.getUsernameByEmail(email);
                String resetLink = "resetPassword?uname=" + username + "&key=" + randomKey;
                String fullName = SQLDCUtility.getFirstName(username) + " " + SQLDCUtility.getLastName(username);
                MailSender.sendResetPasswordMail(email, fullName, resetLink);
                return ErrorCodes.SUCCESS;
            }
            return ErrorCodes.FAILURE;
        }
        return ErrorCodes.WRONGEMAIL;
    }

    /**
     * Reset the password for the user
     *
     * @param username The username of the user
     * @param key      The reset key for the password reset
     * @param password The new password
     * @return The status of the reset action
     */
    public ErrorCodes resetPassword(String username, String key, String password) {
        if (!RegexHelper.checkString(username) || !RegexHelper.checkString(key)) {
            return ErrorCodes.WRONGENTRY;
        } else {
            String salt = SQLDCLogin.getPasswordSalt(username);
            String savedKey = SQLDCLogin.getPasswordKey(username);

            String pwhash = PasswordHasher.hashPassword(password, salt);

            if (key.equals(savedKey)) {
                return SQLDCLogin.setPassword(username, pwhash) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }

            return ErrorCodes.WRONGENTRY;
        }
    }

    /**
     * Fetches the saved user id for the given username from sql.
     * Public because we need to access it from the Login Servlet
     *
     * @param username The username of the required id
     * @return The ID as a String
     */
    public String getUserId(String username) {
        return SQLDCLogin.getUserId(username);
    }

    /**
     * Returns the session identifier for the user
     *
     * @param username The username
     * @return The session identifier
     */
    public String getSessionIdentifier(String username) {
        if (RegexHelper.checkString(username)) {
            String userId = SQLDCLogin.getUserId(username);
            String cookiePostfix = SQLDCLogin.getCookiePostfix(username);

            if (!userId.isEmpty() && !cookiePostfix.isEmpty()) {
                return userId + "-" + cookiePostfix;
            }
        }

        return "";
    }

    /**
     * Extract the user id from the session identifier and check the validity of the session identifier.
     *
     * @param sessionIdentifier The session identifier
     * @return The user id as a String or an empty string in case of an error
     */
    public String getUserIdBySessionIdentifier(String sessionIdentifier) {
        if (RegexHelper.checkString(sessionIdentifier) && !sessionIdentifier.isEmpty()) {
            int splitIndex = sessionIdentifier.indexOf('-');
            String userId = sessionIdentifier.substring(0, splitIndex);
            String cookiePostfix = sessionIdentifier.substring(splitIndex + 1, sessionIdentifier.length());

            String username = SQLDCLogin.getUsername(userId);
            String savedCookiePostfix = SQLDCLogin.getCookiePostfix(username);

            if (savedCookiePostfix.equals(cookiePostfix)) {
                return userId;
            }
        }

        return "";
    }

    /**
     * Return the username of a user by his userId
     *
     * @param userId The userId of the user
     * @return The username of the user
     */
    public String getUsernameById(String userId) {
        if (RegexHelper.checkString(userId)) {
            return SQLDCLogin.getUsername(userId);
        }

        return "";
    }

    /**
     * Gets First Name for User ID
     *
     * @param username The username of the User
     * @return First Name of User
     */
    public String getFirstName(String username) {
        if (RegexHelper.checkString(username) && !username.isEmpty()) {
            return SQLDCUtility.getFirstName(username);
        }
        return "";
    }

    /**
     * Gets Last Name for User ID
     *
     * @param username The username of the User
     * @return Last Name of User
     */
    public String getLastName(String username) {
        if (RegexHelper.checkString(username) && !username.isEmpty()) {
            return SQLDCUtility.getLastName(username);
        }
        return "";
    }

    /**
     * Get the name of the wg of the passed user
     *
     * @param userId The userId of the user
     * @return The wg name
     */
    public String getWgNameByUserId(String userId) {
        if (RegexHelper.checkString(userId)) {
            return SQLDCOverview.getWgName(userId);
        }

        return "";
    }

    /**
     * Return the wgId of the specified user
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public String getWgIdByUserId(String userId) {
        return SQLDCTodo.getWgIdByUser(userId);
    }

    /**
     * Checks if the username is not already in use
     *
     * @param username The username to be checked
     * @return If the username is unique (so it returns true if the name can be used)
     */
    private boolean isUsernameUnique(String username) {
        List<String> usedNames = SQLDCLogin.getAllUserNames();

        return !usedNames.contains(username);
    }

    /**
     * Checks if the email is not already in use
     *
     * @param email The email to be checked
     * @return If the email is unique (so it returns true if the email can be used)
     */
    private boolean isEmailUnique(String email) {
        List<String> usedEmails = SQLDCLogin.getAllEmails();

        return !usedEmails.contains(email);
    }

    /*
      /$$$$$$              /$$     /$$                                               /$$        /$$$$$$              /$$     /$$
     /$$__  $$            | $$    | $$                                              /$$/       /$$__  $$            | $$    | $$
    | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$           /$$/       | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$
    | $$ /$$$$ /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/          /$$/        |  $$$$$$  /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/
    | $$|_  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$          /$$/          \____  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$
    | $$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$        /$$/           /$$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$
    |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/       /$$/           |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/
     \______/  \_______/   \___/   \___/   \_______/|__/      |_______/       |__/             \______/  \_______/   \___/   \___/   \_______/|__/      |_______/
    */

    // Getters and Setters for use with JSPs

    public ErrorCodes getStatus() {
        return this.status;
    }
}
