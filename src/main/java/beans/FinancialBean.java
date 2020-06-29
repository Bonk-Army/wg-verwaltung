package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the financial status page
 */
public class FinancialBean {
    private String userId = "";
    private String wgId = "";

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
            if (!wgId.equals("")) {
                return SQLDCfinancial.createEntry(reason, valueCents, createdBy, wgId, date) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            } else {
                return ErrorCodes.NOWGFOUND;
            }
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
            String savedWgId = SQLDCfinancial.getWgIdOfEntry(entryId);

            if (savedWgId.equals(wgId)) {
                return SQLDCfinancial.setEntryInactive(entryId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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
     * Set the user id
     *
     * @param userId The userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    /**
     * Get all financial record entries for the given wg
     *
     * @param viewLimit How many entries should be fetched
     * @return A List of maps of which each represents one entry
     */
    public List<Map<String, String>> getEntries(int viewLimit) {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);

        return SQLDCfinancial.getAllActiveEntries(wgId, viewLimit);
    }

    /**
     * Get the sum of all entries for each user of the current users wg for an overview table
     *
     * @return A List of maps of which each represents one user
     */
    public List<Map<String, String>> getTotalPerUser() {
        List<Map<String, String>> userNameStringsWithId = SQLDCusers.getAllNameStringsWithUserIdForWg(this.wgId);
        Map<String, Integer> sumPerUserById = SQLDCfinancial.getSumForEveryUserOfWg(this.wgId);
        List<Map<String, String>> userList = new ArrayList<Map<String, String>>();

        for (Map<String, String> user : userNameStringsWithId) {
            Map<String, String> currentUser = new HashMap<String, String>();

            currentUser.put("nameString", user.get("nameString"));

            // If the user has not had any expenses yet, he is not in the sumPerUserById map and
            // the sum fetching would fail, hence checking if the user exists in the map
            if (sumPerUserById.keySet().contains(user.get("userId"))) {
                int sum = sumPerUserById.get(user.get("userId"));
                StringBuilder sb = new StringBuilder();
                Formatter formatter = new Formatter(sb, Locale.GERMAN);
                formatter.format("%,.2f", (sum / 100d));
                String sumString = sb.toString();

                currentUser.put("sum", sumString);
            } else {
                currentUser.put("sum", "0,00");
            }

            userList.add(currentUser);
        }

        return userList;
    }

    /**
     * Get the sum of all entries for the wg of the current user
     *
     * @return The value as a formatted String
     */
    public String getTotalPerWg() {
        int sumForWg = SQLDCfinancial.getTotalForWg(this.wgId);
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.GERMAN);
        formatter.format("%,.2f", (sumForWg / 100d));
        String sumString = sb.toString();

        return sumString;
    }
}
