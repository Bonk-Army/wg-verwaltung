package logic;

import beans.LoginBean;
import beans.SettingsBean;
import beans.ToDoBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToDo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ToDo() {
        super();
    }

    /**
     * Called when the user tries to join a WG via settings page or invite link
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean loginBean = new LoginBean();
        ToDoBean toDoBean = new ToDoBean();
        request.setCharacterEncoding("UTF-8");

        String task = request.getParameter("todo");
        String assignee = request.getParameter("name");
        String dueDateString = request.getParameter("deadline");
        Cookie[] cookies = request.getCookies();
        String sessionIdentifier = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                sessionIdentifier = cookie.getValue();
            }
        }

        String userId = loginBean.getUserIdBySessionIdentifier(sessionIdentifier);
        String assigneeId = loginBean.getUserId(assignee);
        String wgId = toDoBean.getWgIdByUserId(userId);
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-mm-dd").parse(dueDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ErrorCodes status = toDoBean.createTodo(task, assigneeId, wgId, dueDate);

        switch(status){
            case SUCCESS:
                response.sendRedirect("/todo");
                break;
            case FAILURE:
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
            case WRONGENTRY:
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
        }
    }
}
