package beans;

import utilities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean used for the settings page
 */
public class SettingsBean {
    private String userId = "";

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
            ArrayList<String> stringList = SQLDCwgs.getAccessKeyList();
            String accessKey = new RandomStringGenerator(20).nextString();

            // To prevent duplicate keys
            while (stringList.contains(accessKey)) {
                accessKey = new RandomStringGenerator(20).nextString();
            }
            if (SQLDCwgs.createWg(nameWg, accessKey, userId)) {
                String wgId = SQLDCwgs.getWgId(accessKey);
                if (!wgId.equals("")) {
                    if (SQLDCusers.setWgId(wgId, userId) && SQLDCusers.setUserRights(userId, ("wg_admin_" + wgId))) {
                        String userEmail = SQLDCusers.getEmailAddressForUser(userId);
                        String userNameString = SQLDCusers.getNameStringById(userId);
                        String inviteLink = "https://wgverwaltung.azurewebsites.net/joinWGLogic?wgcode=" + accessKey;
                        MailSender.sendWgCreationMail(userNameString, userEmail, nameWg, accessKey, inviteLink);
                        return ErrorCodes.SUCCESS;
                    }
                }
            }
            return ErrorCodes.FAILURE;
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
            return SQLDCwgs.getWgId(accessKey);
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
            return SQLDCusers.getWgIdFromUserId(userId);
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
            String wgId = SQLDCusers.getWgIdFromUserId(userId);

            return SQLDCwgs.getWgNameFromWgId(wgId);
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
            List<String> allAccessKeys = SQLDCwgs.getAccessKeyList();
            if (allAccessKeys.contains(accessKey)) {
                String wgId = SQLDCwgs.getWgId(accessKey);
                if (!wgId.equals("")) {
                    // If there are already 10 (or more, which should technically not happen) members in the wg,
                    // the user can't join and gets an error.
                    if (SQLDCusers.numberOfMembersInWg(wgId) < 10) {
                        return SQLDCusers.setWgId(wgId, userId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
                    }
                    return ErrorCodes.WGFULL;
                }
            } else {
                return ErrorCodes.WRONGENTRY;
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
        String oldPasswordSavedHash = SQLDCusers.getPasswordHash(username);
        String pwsalt = SQLDCusers.getPasswordSalt(username);
        String oldPasswordHash = PasswordHasher.hashPassword(oldPassword, pwsalt);

        if (oldPasswordSavedHash.equals(oldPasswordHash)) {
            String newPasswordHash = PasswordHasher.hashPassword(newPassword, pwsalt);

            return SQLDCusers.setPassword(username, newPasswordHash) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGPASSWORD;
    }

    /**
     * Change the name of the user
     *
     * @param userId    The userId of the user
     * @param firstName The new first name
     * @param lastName  The new last name
     * @return If it was successful
     */
    public ErrorCodes changeName(String userId, String firstName, String lastName) {
        if (RegexHelper.checkString(firstName) && RegexHelper.checkString(lastName)) {
            return SQLDCusers.setName(userId, firstName, lastName) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Clear the wgId for the given user when he wants to leave the wg
     *
     * @param userId The userId of the user
     * @return If it was successful
     */
    public ErrorCodes leaveWg(String userId) {
        return SQLDCusers.clearWg(userId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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

        if (wgId != null) {
            return !wgId.isEmpty();
        }

        return false;
    }

    /**
     * Check if the user has already verified their email address
     *
     * @return If it is verified
     */
    public boolean isEmailVerified() {
        return SQLDCusers.isEmailVerified(this.userId);
    }
}
