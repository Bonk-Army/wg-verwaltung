package beans;

import utilities.*;

import java.util.*;

/**
 * Bean used for the home page (overview page)
 */
public class OverviewBean {
    private String userId;
    private String wgId;

    public OverviewBean() {
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
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @param wgId The ID of the WG
     * @return Number of To-Do's for given WG
     */
    public int getNumberOfTodosWG(String wgId) {
        if (RegexHelper.checkString(wgId) && !wgId.isEmpty()) {
            return SQLDCtodo.countTodo(wgId);
        }
        return -1;
    }

    /**
     * Get number of done To-Do's
     *
     * @param wgId The ID of the WG
     * @return Number of done To-Do's for given WG
     */
    public int getNumberOfDoneWG(String wgId) {
        if (RegexHelper.checkString(wgId) && !wgId.isEmpty()) {
            return SQLDCtodo.countDone(wgId);
        }
        return -1;
    }

    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @param userId The ID of the User
     * @return Number of To-Do's for given User
     */
    public int getNumberOfTodosUser(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCtodo.countTodoUser(userId);
        }
        return -1;
    }

    /**
     * Get number of done To-Do's for User
     *
     * @param userId The ID of the User
     * @return Number of done To-Do's for given User
     */
    public int getNumberOfDoneUser(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCtodo.countDoneUser(userId);
        }
        return -1;
    }

    /**
     * Gets First Name for User ID
     *
     * @param userId The ID of the User
     * @return First Name of User
     */
    public String getFirstName(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCusers.getFirstName(userId);
        }
        return "";
    }

    /**
     * Gets Last Name for User ID
     *
     * @param userId The ID of the User
     * @return Last Name of User
     */
    public String getLastName(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCusers.getLastName(userId);
        }
        return "";
    }

    /**
     * Gets Full Name for User ID
     *
     * @param userId The ID of the User
     * @return Full Name of User
     */
    public String getFullName(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCusers.getFullName(userId);
        }
        return "";
    }

    /**
     * Get the total number of todos for the passed wg
     *
     * @param wgID The wgId of the wg
     * @return The total number of todos
     */
    public int getNumberOfTodos(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCtodo.countTodo(wgID);
        }
        return -1;
    }

    /**
     * Get the number of finished todos for the passed wg
     *
     * @param wgID The wgId of the wg
     * @return The number of finished todos
     */
    public int getNumberOfDone(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCtodo.countDone(wgID);
        }
        return -1;
    }

    /**
     * Return the username of a user by his userId
     *
     * @param userId The userId of the user
     * @return The username of the user
     */
    public String getUsernameById(String userId) {
        if (RegexHelper.checkString(userId)) {
            return SQLDCusers.getUsername(userId);
        }

        return "";
    }

    /**
     * Get the name of the wg of the passed user
     *
     * @param userId The userId of the user
     * @return The wg name
     */
    public String getWgNameByUserId(String userId) {
        if (RegexHelper.checkString(userId)) {
            return SQLDCwgs.getWgName(userId);
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

    /**
     * Get the sum of all financial entry values for the current user
     *
     * @return The sum of all entry values
     */
    public String getExpenseSum() {
        int sumForUser = SQLDCfinancial.getTotalForUser(this.userId, this.wgId);
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.GERMAN);
        formatter.format("%,.2f", (sumForUser / 100d));
        String sumString = sb.toString();

        return sumString;
    }

    /**
     * Get all open todos for the current user
     *
     * @return The number of open todos
     */
    public String getOpenTodosUser() {
        int openTodos = SQLDCtodo.getOpenTodosPerUser(this.userId, this.wgId);

        return String.valueOf(openTodos);
    }

    /**
     * Get all open todos for the current users wg
     *
     * @return The number of open todos
     */
    public String getOpenTodosWg() {
        int openTodos = SQLDCtodo.getOpenTodosPerWg(this.wgId);

        return String.valueOf(openTodos);
    }

    /**
     * Get the total balance of the whole month for the logged in user for the last six months
     *
     * @return The list with the balance for the last six months
     */
    public List<Double> getBalanceChartData() {
        List<Integer> monthlyBalance = SQLDCfinancial.getBalanceDevelopmentForUser(this.userId);
        List<Double> monthlyBalanceConverted = new ArrayList<Double>();

        for (int month : monthlyBalance) {
            monthlyBalanceConverted.add((month / 100d));
        }

        return monthlyBalanceConverted;
    }

    /**
     * Get the number of open todos and their name string for every user of the wg
     *
     * @return A list of maps of which each represents one user
     */
    public List<Map<String, String>> getTodoChartData() {
        return SQLDCtodo.getOpenTodosPerUserOfWg(this.wgId);
    }

    /**
     * Get the timestamp of the last login
     *
     * @return The formatted Date as a String
     */
    public String getLastLogin() {
        return SQLDCusers.getLastLogin(this.userId);
    }
}

