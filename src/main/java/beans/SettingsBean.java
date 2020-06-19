package beans;

import utilities.*;
import java.util.ArrayList;

/**
 * Bean used for the settings page
 */
public class SettingsBean {
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
     * @param name The entered name
     * @param email The entered email address
     * @param subject The entered subject
     * @param message The entered message
     * @return If it was successful
     */
    public ErrorCodes sendContactMail(String name, String email, String subject, String message){
        return MailSender.sendContactRequestMail(name, email, subject, message) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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
}
