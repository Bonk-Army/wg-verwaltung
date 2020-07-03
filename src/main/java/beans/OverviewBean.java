package beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.*;

import java.util.*;

/**
 * Bean used for the home page (overview page)
 */
public class OverviewBean {
    private String userId = "";
    private String wgId = "";

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
     * @return A json object with the name as key and the number of open todos as value
     */
    public String getTodoChartData() {
        Map<String, Integer> todoMap = SQLDCtodo.getOpenTodosPerUserOfWg(this.wgId);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "";

        try {
            json = objectMapper.writeValueAsString(todoMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
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

