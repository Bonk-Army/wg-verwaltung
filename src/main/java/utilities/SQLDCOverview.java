package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLDCOverview extends SQLDatabaseConnection{

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
