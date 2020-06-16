package beans;

// Imports
import utilities.*;

public class OverviewBean {
    // Variables

    // Public Methods
    /**
     * Get Total number of To-Do's
     * Done or not!
     *
     * @param wgID  The ID of the WG
     * @return Number of To-Do's for given WG
     */
    public int getNumberofTodos(String wgID) {return SQLDCOverview.countTodo(wgID); }

    /**
     * Get number of done To-Do's
     *
     * @param wgID The ID of the WG
     * @return Number of done To-Do's for given WG
     */
    public int getNumberofDone(String wgID) {return SQLDCOverview.countDone(wgID); }
    }

    // Private Methods

    // Getter and Setter
