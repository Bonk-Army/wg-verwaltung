package logic;

import beans.ToDoBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Set todo done servlet that is called when the user tries to set a todo to done for their wg
 */
public class SetTodoDone extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetTodoDone() {
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

        ErrorCodes status = toDoBean.setTodoDone(todoId);

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
