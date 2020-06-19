package beans;

import utilities.*;

/**
 * Bean used for the home page (overview page)
 */
public class OverviewBean {
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
    public int getNumberofTodosWG(String wgId) {
        if (RegexHelper.checkString(wgId) && !wgId.isEmpty()) {
            return SQLDCOverview.countTodo(wgId);
        }
        return -1;
    }

    /**
     * Get number of done To-Do's
     *
     * @param wgId The ID of the WG
     * @return Number of done To-Do's for given WG
     */
    public int getNumberofDoneWG(String wgId) {
        if (RegexHelper.checkString(wgId) && !wgId.isEmpty()) {
            return SQLDCOverview.countDone(wgId);
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
    public int getNumberofTodosUser(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCOverview.countTodoUser(userId);
        }
        return -1;
    }

    /**
     * Get number of done To-Do's for User
     *
     * @param userId The ID of the User
     * @return Number of done To-Do's for given User
     */
    public int getNumberofDoneUser(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCOverview.countDoneUser(userId);
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
            return SQLDCOverview.getFirstName(userId);
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
            return SQLDCOverview.getLastName(userId);
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
            return SQLDCOverview.getFullName(userId);
        }
        return "";
    }

    /**
     * Get the total number of todos for the passed wg
     *
     * @param wgID The wgId of the wg
     * @return The total number of todos
     */
    public int getNumberofTodos(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCOverview.countTodo(wgID);
        }
        return -1;
    }

    /**
     * Get the number of finished todos for the passed wg
     *
     * @param wgID The wgId of the wg
     * @return The number of finished todos
     */
    public int getNumberofDone(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCOverview.countDone(wgID);
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
            return SQLDCLogin.getUsername(userId);
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
            return SQLDCOverview.getWgName(userId);
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
}

