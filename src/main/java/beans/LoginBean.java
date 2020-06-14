package beans;

// Imports

import utilities.*;

import java.util.List;

/**
 * Bean that handles all backend logic and database callouts required for user login and registration.
 */
public class LoginBean {
    // Variables

    // Public Methods

    /**
     * Called by LoginServlet upon login action from user. Returns user id in case of successfull login, otherwise
     * returns an empty String
     *
     * @param username The entered username
     * @param password The entered password
     * @return The user id or an empty String.
     */
    public String login(String username, String password) {
        if (!RegexHelper.checkUsername(username)) {
            return "";
        }

        String salt = SQLDCLogin.getPasswordSalt(username);
        String hash = SQLDCLogin.getPasswordHash(username);

        if (!salt.isEmpty() && !hash.isEmpty()) {
            String newHash = PasswordHasher.hashPassword(password, salt);

            return newHash.equals(hash) ? getUserId(username) : "";
        }

        return "";
    }

    /**
     * Called by LoginServlet upon register action from user. Returns the new user id in case the registration is
     * successful, otherwise returns an empty String
     *
     * @param username The entered username (must not exist yet)
     * @param password The entered password
     * @param email    The entered email (must not exist yet)
     * @return The user id or an empty String.
     */
    public String register(String username, String password, String email) {
        //Generate random salt
        String salt = PasswordHasher.generateSalt();

        //Calculate password hash
        String hash = PasswordHasher.hashPassword(password, salt);

        //Call SQL to ask if username / email is unique. If unique, it continues registration process, else it stops
        if (isUsernameUnique(username) && isEmailUnique(email) && RegexHelper.checkUsername(username) && RegexHelper.checkEmail(email)) {
            //Create new user. Generate random, 10-digit verification code for email verification.
            String verificationCode = new RandomStringGenerator(10).nextString();

            // If the user creation was successful, send an email and continue registration
            if (SQLDCLogin.createUser(username, email, hash, new String(salt), verificationCode)) {
                // Now send an email to the user with the verification link
                String verifyLink = "verify?uname=" + username + "&key=" + verificationCode;
                MailSender.sendVerificationMail(email, username, verifyLink);

                // And return the new user id:
                return SQLDCLogin.getUserId(username);
            }
            return "";
        } else {
            //If either already exists, return an empty String
            return "";
        }

    }

    /**
     * Set a user's status to verified
     *
     * @param username         The username of the user to be verified
     * @param verificationCode The verification code the user 'entered'
     * @return If the verification was successful
     */
    public boolean verifyUser(String username, String verificationCode) {
        String savedVerificationCode = SQLDCLogin.getUserVerificationCode(username);

        // If the entered verification code matches the saved one, verify the user
        if (verificationCode.equals(savedVerificationCode)) {
            SQLDCLogin.verifyUser(username);

            return true;
        }

        return false;
    }

    /**
     * Send an email with a link to reset their password to the user
     *
     * @param email The email address that was entered by the user
     * @return If the email has been sent successfully
     */
    public boolean sendPasswordResetLink(String email) {
        if (RegexHelper.checkEmail(email)) {
            String randomKey = new RandomStringGenerator(30).nextString();
            if (SQLDCLogin.setPasswordKey(email, randomKey)) {
                String username = SQLDCLogin.getUsernameByEmail(email);
                String resetLink = "resetPassword?uname=" + username + "&key=" + randomKey;
                MailSender.sendResetPasswordMail(email, username, resetLink);
                return true;
            }
        }
        return false;
    }

    /**
     * Reset the password for the user
     *
     * @param username The username of the user
     * @param key      The reset key for the password reset
     * @param password The new password
     * @return If it was successful
     */
    public boolean resetPassword(String username, String key, String password) {
        String salt = SQLDCLogin.getPasswordSalt(username);
        String savedKey = SQLDCLogin.getPasswordKey(username);

        String pwhash = PasswordHasher.hashPassword(password, salt);

        if (key.equals(savedKey)) {
            return SQLDCLogin.setPassword(username, pwhash);
        }

        return false;
    }

    /**
     * Fetches the saved user id for the given username from sql
     *
     * @param username The username of the required id
     * @return The ID as a String
     */
    private String getUserId(String username) {
        return SQLDCLogin.getUserId(username);
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

    // Getter and Setter

    // getUser() { return this.User; }
}
