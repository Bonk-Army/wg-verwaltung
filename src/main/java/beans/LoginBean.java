package beans;

// Imports

import utilities.*;

import java.util.List;
import java.util.Random;

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
        String salt = SQLDatabaseConnection.getPasswordSalt(username);
        String hash = SQLDatabaseConnection.getPasswordHash(username);

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
        if (isUsernameUnique(username) && isEmailUnqiue(email) && RegexHelper.checkUsername(username) && RegexHelper.checkEmail(email)) {
            //Create new user. Generate random, 10-digit verification code for email verification.
            String verificationCode = new RandomStringGenerator(10).nextString();

            // If the user creation was successful, send an email and continue registration
            if (SQLDatabaseConnection.createUser(username, email, hash, new String(salt), verificationCode)) {
                // Now send an email to the user with the verification link
                MailSender.sendEmail(email, "Willkommen bei wg-verwaltung!", ("Bitte bestätige noch kurz deine "
                        + "E-Mail-Adresse, indem du auf den folgenden Link klickst oder ihn in deinem Browser eingibst: "
                        + "https://wgverwaltung.azurewebsites.net/verify?uname=" + username + "&key=" + verificationCode));

                // And return the new user id:
                return SQLDatabaseConnection.getUserId(username);
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
        String savedVerificationCode = SQLDatabaseConnection.getUserVerificationCode(username);

        // If the entererd verification code matches the saved one, verify the user
        if (verificationCode.equals(savedVerificationCode)) {
            SQLDatabaseConnection.verifyUser(username);

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
            if (SQLDatabaseConnection.setPasswordKey(email, randomKey)) {
                String username = SQLDatabaseConnection.getUsernameByEmail(email);
                String resetLink = "https://wgverwaltung.azurewebsites.net/resetPassword?uname=" + username + "&key=" + randomKey;
                MailSender.sendEmail(email, "Hat da jemand sein Passwort vergessen?",
                        "Hier ist der Link zum Zurücksetzen: " + resetLink);
                return true;
            }
        }
        return false;
    }

    public boolean resetPassword(String username, String key, String password){
        String salt = SQLDatabaseConnection.getPasswordSalt(username);
        String savedKey = SQLDatabaseConnection.getPasswordKey(username);

        String pwhash = PasswordHasher.hashPassword(password, salt);

        if(key.equals(savedKey)){
            if(SQLDatabaseConnection.setPassword(username, pwhash)){
                return true;
            }
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
        return SQLDatabaseConnection.getUserId(username);
    }

    /**
     * Checks if the username is not already in use
     *
     * @param username The username to be checked
     * @return If the username is unique (so it returns true if the name can be used)
     */
    private boolean isUsernameUnique(String username) {
        List<String> usedNames = SQLDatabaseConnection.getAllUserNames();

        return !usedNames.contains(username);
    }

    /**
     * Checks if the email is not already in use
     *
     * @param email The email to be checked
     * @return If the email is unique (so it returns true if the email can be used)
     */
    private boolean isEmailUnqiue(String email) {
        List<String> usedEmails = SQLDatabaseConnection.getAllEmails();

        return !usedEmails.contains(email);
    }

    // Getter and Setter

    // getUser() { return this.User; }
}
