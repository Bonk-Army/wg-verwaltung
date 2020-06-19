package logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ToDoBean;
import utilities.ErrorCodes;

/**
 * Remove Todo servlet that is called when the users tries to delete a todo from his wg
 */
public class RemoveTodo extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public RemoveTodo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToDoBean toDoBean = new ToDoBean();
        request.setCharacterEncoding("UTF-8");

        String todoId = request.getParameter("todoId");

        ErrorCodes status = toDoBean.removeTodo(todoId);

        switch (status) {
        case SUCCESS:
            //Show success
            response.sendRedirect("/todo");
            break;
        case FAILURE:
            //Show failure
            request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
            break;
        }
    }
}