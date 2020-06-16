package beans;

// Imports

import utilities.*;

public class OverviewBean {
    public OverviewBean() {
    }
    // Variables

    // Public Methods
    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @param wgID  The ID of the WG
     * @return Number of To-Do's for given WG
     */
    public int getNumberofTodosWG(String wgID) {return SQLDCOverview.countTodo(wgID); }

    /**
     * Get number of done To-Do's
     *
     * @param wgID The ID of the WG
     * @return Number of done To-Do's for given WG
     */
    public int getNumberofDoneWG(String wgID) {return SQLDCOverview.countDone(wgID); }

    /**
     * Get Total number of To-Do's for WG
     * Done or not!
     *
     * @param userId  The ID of the User
     * @return Number of To-Do's for given User
     */
    public int getNumberofTodosUser(String userId) {return SQLDCOverview.countTodoUser(userId); }

    /**
     * Get number of done To-Do's for User
     *
     * @param userId The ID of the User
     * @return Number of done To-Do's for given User
     */
    public int getNumberofDoneUser(String userId) {return SQLDCOverview.countDoneUser(userId); }

    /**
     * Gets First Name for User ID
     * @param userId The ID of the User
     * @return First Name of User
     */
    public String getFirstName(String userId) {return SQLDCOverview.getFirstName(userId); }

    /**
     * Gets Last Name for User ID
     * @param userId The ID of the User
     * @return Last Name of User
     */
    public String getLastName(String userId) {return SQLDCOverview.getLastName(userId); }

    /**
     * Gets Full Name for User ID
     * @param userId The ID of the User
     * @return Full Name of User
     */
    public String getFullName(String userId) {return SQLDCOverview.getFullName(userId); }

    public static int getNumberofTodos(String wgID) {
        return SQLDCOverview.countTodo(wgID);
    }

    public static int getNumberofDone(String wgID) {
        return SQLDCOverview.countDone(wgID);
    }
}

// Private Methods

    // Getter and Setter
// Getter and Setter
