package beans;


import utilities.RegexHelper;
import utilities.SQLDCTodo;

import java.util.Date;

public class ToDoBean {
    /**
     * Create a to-do
     *
     * @param task    The task of the user
     * @param userId  The ID of the user
     * @param wgId    The ID of the WG
     * @param dateDue The date til the task should be done
     * @return If the to-do has been created successfully. If not, the user has to be informed!
     */
    public static boolean createTodo(String task, String userId, String wgId, Date dateDue) {
        if (RegexHelper.checkString(task)) {
            return SQLDCTodo.createTodo(task, userId, wgId, dateDue);
        }
        return false;
    }
}
