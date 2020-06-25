package logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CleanBean;
import beans.SessionBean;
import utilities.ErrorCodes;

/**
 * Called when the user wants to add a new cleaning task
 */
public class AddCleaningTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddCleaningTask() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user wants to create a new cleaning task
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CleanBean cleanBean = new CleanBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String userId = sessionBean.getUserId();
        String wgId = sessionBean.getWgId();

        String taskname = request.getParameter("taskname");

        ErrorCodes status = cleanBean.addNewTask(taskname, wgId);

        switch (status) {
        case SUCCESS:
            //Show success
            response.sendRedirect("/clean");
            break;
        case FAILURE:
            //Show failure
            request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
            break;
        case WRONGENTRY:
            request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
            break;
        }
    }
}
