package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLDCOverview extends SQLDatabaseConnection{

    public static int countTodo(int wg_id) {
        int numberofTodo;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wg_id +";");
            numberofTodo = rs.getInt(1);
            return numberofTodo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static int countDone(int wg_id) {
        int numberofDone;
        try {
            ResultSet rs = executeQuery("SELECT COUNT(*) FROM todo WHERE wgID=" + wg_id + " AND isDone=1;");
            numberofDone = rs.getInt(1);
            return numberofDone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
