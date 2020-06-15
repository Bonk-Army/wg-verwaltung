package beans;


import models.TodoModel;
import utilities.DateFormatter;
import utilities.RegexHelper;
import utilities.SQLDCTodo;

import java.util.ArrayList;
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

    public static String dateToString(Date date) {
        return DateFormatter.dateToString(date);
    }

    public static ArrayList<TodoModel> getAllTodos(String wgId) {
        return SQLDCTodo.getAllTodos(wgId);
    }
}
