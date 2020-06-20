package beans;

import utilities.*;

import java.util.ArrayList;

/**
 * Bean used for the settings page
 */
public class SettingsBean {
    private String userId;

    public SettingsBean() {
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
     * Create a wg
     * Automatically set wgId of user to created wgId
     *
     * @param userId the ID of the user who creates a wg
     * @param nameWg the name of the wg
     * @return If created successfully
     */
    public ErrorCodes createWg(String userId, String nameWg) {
        if (RegexHelper.checkString(nameWg)) {
            ArrayList<String> stringList = SQLDCSettings.getAccessKeyList();
            String accessKey = new RandomStringGenerator(20).nextString();
            while (stringList.contains(accessKey)) {
                accessKey = new RandomStringGenerator(20).nextString();
            }
            if (SQLDCSettings.createWg(nameWg, accessKey)) {
                String wgId = SQLDCSettings.getWgId(accessKey);
                if (!wgId.equals("")) {
                    if (SQLDCSettings.setWgId(wgId, userId) && SQLDCSettings.setUserRights(userId, UserRights.WG_ADMIN.getSqlKey())) {
                        return ErrorCodes.SUCCESS;
                    }
                    return ErrorCodes.FAILURE;
                }
            }
        }
        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Get the ID of a wg via access key
     *
     * @param accessKey the access key for the wg
     * @return the wgId
     */
    public String getWgId(String accessKey) {
        if (RegexHelper.checkString(accessKey)) {
            return SQLDCSettings.getWgId(accessKey);
        }
        return "";
    }

    /**
     * Returns the wgId of the user's wg
     *
     * @param userId The userId of the user
     * @return The wgId
     */
    public String getWgIdFromUserId(String userId) {
        if (RegexHelper.checkString(userId)) {
            return SQLDCUtility.getWgIdFromUserId(userId);
        }

        return "";
    }

    /**
     * Return the wg name of the user's wg
     *
     * @param userId The userId of the user
     * @return The wg name
     */
    public String getWgNameFromUserID(String userId) {
        if (RegexHelper.checkString(userId)) {
            String wgId = SQLDCUtility.getWgIdFromUserId(userId);

            return SQLDCUtility.getWgNameFromWgId(wgId);
        }

        return "";
    }

    /**
     * Set the ID of the wg for a user
     *
     * @param userId    the user who changes wg
     * @param accessKey the access key of the wg
     * @return If set was successful
     */
    public ErrorCodes setWgId(String userId, String accessKey) {
        if (RegexHelper.checkString(accessKey)) {
            String wgId = SQLDCSettings.getWgId(accessKey);
            if (!wgId.equals("")) {
                return SQLDCSettings.setWgId(wgId, userId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }
        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Send a mail concerning the contact request from the contact form
     *
     * @param name    The entered name
     * @param email   The entered email address
     * @param subject The entered subject
     * @param message The entered message
     * @return If it was successful
     */
    public ErrorCodes sendContactMail(String name, String email, String subject, String message) {
        return MailSender.sendContactRequestMail(name, email, subject, message) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
    }

    /**
     * Change the users password
     *
     * @param username    The username of the user
     * @param oldPassword The old password
     * @param newPassword The new password
     * @return If it was successful
     */
    public ErrorCodes changePassword(String username, String oldPassword, String newPassword) {
        String oldPasswordSavedHash = SQLDCLogin.getPasswordHash(username);
        String pwsalt = SQLDCLogin.getPasswordSalt(username);
        String oldPasswordHash = PasswordHasher.hashPassword(oldPassword, pwsalt);

        if (oldPasswordSavedHash.equals(oldPasswordHash)) {
            String newPasswordHash = PasswordHasher.hashPassword(newPassword, pwsalt);

            return SQLDCLogin.setPassword(username, newPasswordHash) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGPASSWORD;
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


    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Check if the user has a wg
     *
     * @return true if he has a wg, false if not
     */
    public boolean getUserHasWg() {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);

        return !wgId.isEmpty();
    }
}
