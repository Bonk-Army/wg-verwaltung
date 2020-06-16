package beans;

// Imports
import utilities.*;

public class OverviewBean {
    // Variables

    // Public Methods
    public int getNumberofTodos(String wgID) {return SQLDCOverview.countTodo(wgID); }
    public int getNumberofDone(String wgID) {return SQLDCOverview.countDone(wgID); }
    }

    // Private Methods

    // Getter and Setter
}
