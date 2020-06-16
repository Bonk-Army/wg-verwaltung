package beans;

// Imports

import utilities.*;

public class OverviewBean {
    public OverviewBean() {
    }
    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @param wgId  The ID of the WG
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
     * @param userId  The ID of the User
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
     * @param userId The ID of the User
     * @return Full Name of User
     */
    public String getFullName(String userId) {
        if (RegexHelper.checkString(userId) && !userId.isEmpty()) {
            return SQLDCOverview.getFullName(userId);
        }
        return "";
    }
    public static int getNumberofTodos(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCOverview.countTodo(wgID);
        }
        return -1;
    }
    public static int getNumberofDone(String wgID) {
        if (RegexHelper.checkString(wgID) && !wgID.isEmpty()) {
            return SQLDCOverview.countDone(wgID);
        }
        return -1;
    }

}

