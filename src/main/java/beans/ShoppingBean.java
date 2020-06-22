package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the shopping plan page
 */
public class ShoppingBean {
    private String userId = "";
    private String wgId = "";
    private String username = "";

    public ShoppingBean() {
    }

    // Variables

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
     * Create a new shopping request
     *
     * @param article     The article to be purchased
     * @param amount      The amount to be purchased
     * @param createdBy   The user id of the user that created the request
     * @param requestedBy The user id of the user that requested the article
     * @param dateDue     The date when the article should have been purchased
     * @return If it was successful
     */
    public ErrorCodes createRequest(String article, String amount, String createdBy, String requestedBy, Date dateDue) {
        if (RegexHelper.checkText(article) && RegexHelper.checkText(amount) && RegexHelper.checkString(requestedBy)) {
            String wgId = new LoginBean().getWgIdByUserId(createdBy);

            return SQLDCShopping.addArticleRequest(article, amount, createdBy, requestedBy, dateDue, wgId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Set a request to done
     *
     * @param requestId The requestId of the request that has to be set to done
     * @return If it was successful
     */
    public ErrorCodes setRequestDone(String requestId, String wgId) {
        if (RegexHelper.checkString(requestId)) {
            String savedWgId = SQLDCTodo.getWgIdOfTodo(requestId);

            if (savedWgId.equals(wgId)) {
                return SQLDCShopping.setArticleRequestDone(requestId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
            }
        }

        return ErrorCodes.WRONGENTRY;
    }

    /**
     * Returns the first name and the first letter of the last name of a user as one string
     *
     * @param username The username of the user
     * @return The String, e.g. Max M
     */
    public String getNameString(String username) {
        if (RegexHelper.checkString(username)) {
            return SQLDCUtility.getNameString(username);
        }

        return "";
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

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get all open shopping requests for the wg of the currently logged in user
     *
     * @return A List of Maps of which each represents an article
     */
    public List<Map<String, String>> getRequests() {
        return SQLDCShopping.getActiveArticleRequests(this.wgId);
    }

    /**
     * Return a List of the names (Format: Max M for Max Mustermann) of the users in the wg of the specified user
     *
     * @return A List of Maps of which each contains the username and the formatted name string
     */
    public List<Map<String, String>> getUsersOfWg() {
        List<Map<String, String>> userList = SQLDCLogin.getAllNameStringsForWg(this.wgId);
        List<Map<String, String>> newList = new ArrayList<Map<String, String>>();

        for (Map<String, String> user : userList) {
            if (user.get("username").equals(this.username)) {
                newList.add(user);
                userList.remove(user);

                newList.addAll(userList);

                return newList;
            }
        }

        return newList;
    }
}
