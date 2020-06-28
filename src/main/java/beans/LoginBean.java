package beans;

import utilities.*;

import java.util.List;

/**
 * Bean that handles all backend logic and database callouts required for user login and registration.
 */
public class LoginBean {
    private String cookiePostfixNotHashed = "";

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
     * @param username       The entered username
     * @param password       The entered password
     * @param cookieLifetime The lifetime of the session cookie in days
     * @return The status of the request
     */
    public ErrorCodes login(String username, String password, int cookieLifetime) {
        List<String> allUserNames = SQLDCusers.getAllUserNames();

        if (!RegexHelper.checkString(username) || !allUserNames.contains(username)) {
            return ErrorCodes.WRONGUNAME;
        }

        String salt = SQLDCusers.getPasswordSalt(username);
        String hash = SQLDCusers.getPasswordHash(username);
        String cookiePostfix = new RandomStringGenerator(21).nextString();
        this.cookiePostfixNotHashed = cookiePostfix;
        String cookiePostfixHash = PasswordHasher.hashPassword(cookiePostfix, salt);
        String userId = SQLDCusers.getUserId(username);

        if (cookiePostfixHash == null) {
            return ErrorCodes.FAILURE;
        }

        if (!salt.isEmpty() && !hash.isEmpty()) {
            String newHash = PasswordHasher.hashPassword(password, salt);

            //Login was either successful or one of the entered params was wrong
            if (newHash.equals(hash)) {
                if (SQLDCusers.setCookiePostfix(username, cookiePostfixHash) && SQLDCusers.setCookieLifetime(username, cookieLifetime)
                        && SQLDCusers.setLastPasswordLogin(username)) {
                    setLastLogin(userId);
                    return ErrorCodes.SUCCESS;
                } else {
                    return ErrorCodes.FAILURE;
                }
            } else {
                return ErrorCodes.WRONGENTRY;
            }
        }

        //Something failed server-side, return FAILURE
        return ErrorCodes.FAILURE;
    }

    /**
     * Called by LoginServlet upon register action from user. Returns the new user id in case the registration is
     * successful, otherwise returns an empty String
     *
     * @param username       The entered username (must not exist yet)
     * @param password       The entered password
     * @param email          The entered email (must not exist yet)
     * @param cookieLifetime The lifetime of the session cookie in days
     * @return The status of the request
     */
    public ErrorCodes register(String username, String password, String email, String firstName, String lastName, int cookieLifetime) {
        //Generate random salt
        String salt = PasswordHasher.generateSalt();

        //Calculate password hash
        String hash = PasswordHasher.hashPassword(password, salt);

        if (hash == null) {
            return ErrorCodes.FAILURE;
        }

        //Call SQL to ask if username / email is unique. If unique, it continues registration process, else it stops
        if (!RegexHelper.checkString(username) || !RegexHelper.checkText(firstName) || !RegexHelper.checkText(lastName) || !RegexHelper.checkEmail(email)) {
            return ErrorCodes.WRONGENTRY;
        } else {
            if (isUsernameUnique(username)) {
                if (isEmailUnique(email)) {
                    //Create new user. Generate random, 10-digit verification code for email verification.
                    String verificationCode = new RandomStringGenerator(10).nextString();
                    String cookiePostfix = new RandomStringGenerator(21).nextString();
                    this.cookiePostfixNotHashed = cookiePostfix;
                    String cookiePostfixHash = PasswordHasher.hashPassword(cookiePostfix, hash);

                    if (cookiePostfixHash == null) {
                        return ErrorCodes.FAILURE;
                    }

                    // If the user creation was successful, send an email and continue registration
                    if (SQLDCusers.createUser(username, email, hash, new String(salt), verificationCode, firstName, lastName, cookiePostfixHash, cookieLifetime)) {
                        // Now send an email to the user with the verification link
                        String verifyLink = "verify?uname=" + username + "&key=" + verificationCode;
                        String fullName = firstName + " " + lastName;
                        MailSender.sendVerificationMail(email, fullName, verifyLink);

                        // Set the lastLogin time
                        String userId = SQLDCusers.getUserId(username);
                        setLastLogin(userId);

                        // And return success
                        return ErrorCodes.SUCCESS;
                    }
                    // If something failed server-side, return FAILURE
                    return ErrorCodes.FAILURE;
                } else {
                    return ErrorCodes.DUPLICATEEMAIL;
                }
            } else {
                //If either already exists, return a duplicate username error
                return ErrorCodes.DUPLICATEUNAME;
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
            String savedVerificationCode = SQLDCusers.getUserVerificationCode(username);

            // If the entered verification code matches the saved one, verify the user
            if (verificationCode.equals(savedVerificationCode)) {
                SQLDCusers.verifyUser(username);

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
            List<String> knownMailAddresses = SQLDCusers.getAllEmails();

            if (knownMailAddresses.contains(email)) {
                String randomKey = new RandomStringGenerator(30).nextString();
                if (SQLDCusers.setPasswordKey(email, randomKey)) {
                    String username = SQLDCusers.getUsernameByEmail(email);
                    String resetLink = "resetPassword?uname=" + username + "&key=" + randomKey;
                    String fullName = SQLDCusers.getFirstName(username) + " " + SQLDCusers.getLastName(username);
                    MailSender.sendResetPasswordMail(email, fullName, resetLink);
                    return ErrorCodes.SUCCESS;
                }
                return ErrorCodes.FAILURE;
            }
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
            String salt = SQLDCusers.getPasswordSalt(username);
            String savedKey = SQLDCusers.getPasswordKey(username);

            String pwhash = PasswordHasher.hashPassword(password, salt);

            if (key.equals(savedKey)) {
                return SQLDCusers.setPassword(username, pwhash) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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
        if (RegexHelper.checkString(username)) {
            return SQLDCusers.getUserId(username);
        }

        return "";
    }

    /**
     * Returns the session identifier for the user
     *
     * @param username The username
     * @return The session identifier
     */
    public String getSessionIdentifier(String username) {
        if (RegexHelper.checkString(username)) {
            String userId = SQLDCusers.getUserId(username);
            String cookiePostfix = SQLDCusers.getCookiePostfix(username);

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

            String username = SQLDCusers.getUsername(userId);

            String pwsalt = SQLDCusers.getPasswordSalt(username);
            String cookiePostfixHash = PasswordHasher.hashPassword(cookiePostfix, pwsalt);
            String savedCookiePostfixHash = SQLDCusers.getCookiePostfix(username);

            if (savedCookiePostfixHash.equals(cookiePostfixHash)) {
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
            return SQLDCusers.getUsername(userId);
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
            return SQLDCusers.getFirstName(username);
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
            return SQLDCusers.getLastName(username);
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
            return SQLDCwgs.getWgName(userId);
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
        return SQLDCusers.getWgIdByUser(userId);
    }

    /**
     * Checks if the username is not already in use
     *
     * @param username The username to be checked
     * @return If the username is unique (so it returns true if the name can be used)
     */
    private boolean isUsernameUnique(String username) {
        List<String> usedNames = SQLDCusers.getAllUserNames();

        return !usedNames.contains(username);
    }

    /**
     * Checks if the email is not already in use
     *
     * @param email The email to be checked
     * @return If the email is unique (so it returns true if the email can be used)
     */
    private boolean isEmailUnique(String email) {
        List<String> usedEmails = SQLDCusers.getAllEmails();

        return !usedEmails.contains(email);
    }

    /**
     * Set last login time for the user that recently logged in
     *
     * @param userId The userId of the user
     * @return If it was successful
     */
    public boolean setLastLogin(String userId) {
        return SQLDCusers.setLastLogin(userId);
    }

    /**
     * Check if the session cookie for the user is still valid
     *
     * @param userId The userId of the user
     * @return If it is still valid
     */
    public boolean isSessionCookieStillValid(String userId) {
        return SQLDCusers.isSessionCookieStillValid(userId);
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

    public String getCookiePostfixNotHashed() {
        return cookiePostfixNotHashed;
    }
}
