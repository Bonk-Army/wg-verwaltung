package beans;

import utilities.RandomStringGenerator;
import utilities.RegexHelper;
import utilities.SQLDCSettings;

public class SettingsBean {
    /**
     * Create a wg
     * Automatically set wgId of user to created wgId
     *
     * @param userId the ID of the user who creates a wg
     * @param nameWg the name of the wg
     * @return If created successfully -> true
     */
    public static boolean createWg(String userId, String nameWg) {
        if (RegexHelper.checkUsername(nameWg)) {
            String accessKey = new RandomStringGenerator(20).nextString();
            if (SQLDCSettings.createWg(nameWg, accessKey)) {
                String wgId = SQLDCSettings.getWgId(accessKey);
                if (!wgId.equals("")) {
                    return SQLDCSettings.setWgId(wgId, userId);
                }
            }
        }
        return false;
    }

    /**
     * Get the ID of a wg via access key
     *
     * @param accessKey the access key for the wg
     * @return the wgId
     */
    public static String getWgId(String accessKey) {
        if (RegexHelper.checkUsername(accessKey)) {
            return SQLDCSettings.getWgId(accessKey);
        }
        return "";
    }

    /**
     * Set the ID of the wg for a user
     *
     * @param userId the user who changes wg
     * @param accessKey the access key of the wg
     * @return If set was successful -> true
     */
    public static boolean setWgId(String userId, String accessKey) {
        if (RegexHelper.checkUsername(accessKey)) {
            String wgId = SQLDCSettings.getWgId(accessKey);
            if (!wgId.equals("")) {
                return SQLDCSettings.setWgId(wgId, userId);
            }
        }
        return false;
    }
}
