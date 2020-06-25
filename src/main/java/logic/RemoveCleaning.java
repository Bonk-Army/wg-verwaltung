package logic;

import beans.CleanBean;
import beans.FinancialBean;
import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Called when the user tries to remove a cleaning task from his wg
 */
public class RemoveCleaning extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveCleaning() {
        super();
    }

    /**
     * Called when the user tries to remove a cleaning task from his wg
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CleanBean cleanBean = new CleanBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String wgId = sessionBean.getWgId();

        String taskId = request.getParameter("taskId");

        ErrorCodes status = cleanBean.removeCleaningTask(taskId, wgId);

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
