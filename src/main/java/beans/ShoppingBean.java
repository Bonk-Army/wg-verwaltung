package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the shopping plan page
 */
public class ShoppingBean {
    private String userId = "";

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
    public ErrorCodes setRequestDone(String requestId) {
        if (RegexHelper.checkString(requestId)) {
            return SQLDCShopping.setArticleRequestDone(requestId) ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;
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
            return SQLDCTodo.getNameString(username);
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

    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * Get all open shopping requests for the wg of the currently logged in user
     *
     * @return A List of Maps of which each represents an article
     */
    public List<Map<String, String>> getRequests() {
        String wgId = new LoginBean().getWgIdByUserId(this.userId);

        return SQLDCShopping.getActiveArticleRequests(wgId);
    }

    /**
     * Return a List of the names (Format: Max M for Max Mustermann) of the users in the wg of the specified user
     *
     * @return A List of Maps of which each contains the username and the formatted name string
     */
    public List<Map<String, String>> getUsersOfWg() {
        String wgId = SQLDCTodo.getWgIdByUser(userId);
        String currentUsername = new LoginBean().getUsernameById(userId);

        List<String> usernames = SQLDCTodo.getAllUsersOfWG(wgId);
        usernames.remove(currentUsername);
        List<Map<String, String>> formattedNames = new ArrayList<Map<String, String>>();

        List<String> usernamesSorted = new ArrayList<String>();
        usernamesSorted.add(currentUsername);
        usernamesSorted.addAll(usernames);

        for (String username : usernamesSorted) {
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("username", username);
            userMap.put("nameString", getNameString(username));

            formattedNames.add(userMap);
        }

        return formattedNames;
    }
}
