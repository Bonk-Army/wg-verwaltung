package logic;

import beans.SessionBean;
import beans.ToDoBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove Todo servlet that is called when the users tries to delete a todo from his wg
 */
public class RemoveTodo extends HttpServlet {
    public RemoveTodo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToDoBean toDoBean = new ToDoBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String todoId = request.getParameter("todoId");
        String wgId = sessionBean.getWgId();

        boolean isMyTodoPage = Boolean.parseBoolean(request.getParameter("isMyTodo"));

        ErrorCodes status = toDoBean.removeTodo(todoId, wgId);

        if (status == ErrorCodes.SUCCESS) {
            //Show success
            if (isMyTodoPage) {
                response.sendRedirect("/mytodo");
            } else {
                response.sendRedirect("/todo");
            }
        } else {
            //Show failure
            request.setAttribute("isSadLlama", true);
            request.setAttribute("header", status.getHeader());
            request.setAttribute("message", status.getMessage());
            request.getServletContext().getRequestDispatcher("/status").forward(request, response);
        }
    }
}