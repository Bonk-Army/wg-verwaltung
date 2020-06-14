package utilities;

import java.util.Date;
import java.sql.ResultSet;

public class SQLDCTodo extends SQLDatabaseConnection {
    /**
     * Create a to-do in the database
     *
     * @param task    The task of the user
     * @param userId  The ID of the user
     * @param wgId    The ID of the WG
     * @param dateDue The date til the task should be done
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue) {
        try {
            Date dateCreated = new Date();
            ResultSet rs = executeQuery(("INSERT INTO todo (task, userId, wgId, dateCreated, dateDue, isDone)"
                    + "VALUES ('" + task + "', '" + userId + "', '" + wgId + "', '" + dateCreated + "', '" + dateDue + "', '" + false + "')"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
