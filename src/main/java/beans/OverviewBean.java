package beans;

// Imports

import utilities.*;

public class OverviewBean {
    public OverviewBean() {
    }
    // Variables

    // Public Methods
    public static int getNumberofTodos(String wgID) {
        return SQLDCOverview.countTodo(wgID);
    }

    public static int getNumberofDone(String wgID) {
        return SQLDCOverview.countDone(wgID);
    }
}

// Private Methods

// Getter and Setter
