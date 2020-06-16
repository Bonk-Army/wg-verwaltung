package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLDCOverview extends SQLDatabaseConnection{
    /**
     * Sends SQL Query in order to count number of To-Do's for specific WG
     * @param wgId The ID of the WG
     * @return Number of To-Do's for given WG, done or not
     */
    public static int countTodo(String wgId) {
        int numberofTodo;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wgId +";");
            numberofTodo = rs.getInt(1);
            return numberofTodo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Sends SQL Query in order to count the number of done To-Do's for specific WG
     * @param wgId The ID of the WG
     * @return Number of done To-Do's
     */
    public static int countDone(String wgId) {
        int numberofDone;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wgId + " AND isDone=1;");
            numberofDone = rs.getInt(1);
            return numberofDone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}