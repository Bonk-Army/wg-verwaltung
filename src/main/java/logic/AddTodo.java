package logic;

import beans.LoginBean;
import beans.SessionBean;
import beans.ToDoBean;
import utilities.ErrorCodes;
import utilities.RegexHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create Todo servlet that is called when the user tries to create a new todo for his wg
 */
public class AddTodo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTodo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user wants to add a new todo
     *
     * @param request  The POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean loginBean = new LoginBean();
        ToDoBean toDoBean = new ToDoBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String task = request.getParameter("todo");
        String assignee = request.getParameter("username");
        String dueDateString = request.getParameter("date");
        String dueDateTime = request.getParameter("time");
        dueDateString = (dueDateString + " " + dueDateTime);

        String userId = sessionBean.getUserId();

        String assigneeId = loginBean.getUserId(assignee);
        String wgId = sessionBean.getWgId();
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dueDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ErrorCodes status = toDoBean.createTodo(task, assigneeId, wgId, dueDate, userId);

        switch (status) {
            case SUCCESS:
                //Redirect back to the todo page
                response.sendRedirect("/todo");
                break;
            default:
                //Show failure
                request.setAttribute("isSadLlama", true);
                request.setAttribute("header", status.getHeader());
                request.setAttribute("message", status.getMessage());
                request.getServletContext().getRequestDispatcher("/status").forward(request, response);
                break;
        }
    }
}
