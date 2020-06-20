package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the financial status page
 */
public class FinancialBean {
    private String userId = "";

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
     * Add a new financial entry
     *
     * @param reason    The reason for the entry
     * @param value     The value of the entry in Euros
     * @param createdBy The userId of the user that created the entry
     * @param wgId      The wgId of the wg of the user
     * @return If it was successful
     */
    public ErrorCodes addFinancialEntry(String reason, String value, String createdBy, String wgId, Date date) {
        int valueCents = (int) Math.round(100 * Double.parseDouble(value));

        if (RegexHelper.checkText(reason)) {
            return SQLDCFinancial.createEntry(reason, valueCents, createdBy, wgId, date) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Remove a financial entry if the user is allowed to do so
     *
     * @param entryId The entryId of the entry to be removed
     * @param wgId    The wgId of the user to check if the entry belongs to the users wg
     * @return If it was successful
     */
    public ErrorCodes removeFinancialEntry(String entryId, String wgId) {
        if (RegexHelper.checkString(entryId)) {
            String savedWgId = SQLDCFinancial.getWgIdOfEntry(entryId);

            if (savedWgId.equals(wgId)) {
                return SQLDCFinancial.setEntryInactive(entryId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }

        return ErrorCodes.WRONGENTRY;
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

    /**
     * Get all financial record entries for the given wg
     *
     * @param viewLimit How many entries should be fetched
     * @return A List of maps of which each represents one entry
     */
    public List<Map<String, String>> getEntries(int viewLimit) {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);

        return SQLDCFinancial.getAllActiveEntries(wgId, viewLimit);
    }

    /**
     * Get the sum of all entries for each user of the current users wg for a diagram
     *
     * @return A List of maps of which each represents one user
     */
    public List<Map<String, String>> getTotalPerUser() {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);
        List<String> userIds = SQLDCFinancial.getAllUserIdsOfWg(wgId);

        List<Map<String, String>> userList = new ArrayList<Map<String, String>>();

        for (String userId : userIds) {
            Map<String, String> user = new HashMap<String, String>();

            int sumForUser = SQLDCFinancial.getTotalForUser(userId);
            String sumString = String.valueOf((sumForUser / 100));

            String username = SQLDCLogin.getUsername(userId);
            user.put("name", SQLDCUtility.getNameString(username));
            user.put("sum", sumString);

            userList.add(user);
        }

        return userList;
    }

    /**
     * Get the sum of all entries for the wg of the current user
     *
     * @return The value as a formatted String
     */
    public String getTotalPerWg() {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);

        int sumForWg = SQLDCFinancial.getTotalForWg(wgId);
        String sumString = String.valueOf((sumForWg / 100));

        return sumString;
    }

    /**
     * Set the user id
     *
     * @param userId The userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
