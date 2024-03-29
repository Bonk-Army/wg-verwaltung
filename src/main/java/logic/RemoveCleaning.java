package logic;

import beans.CleanBean;
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

        if (status == ErrorCodes.SUCCESS) {
            //Show success
            response.sendRedirect("/clean");
        } else {
            //Show failure
            request.setAttribute("isSadLlama", true);
            request.setAttribute("header", status.getHeader());
            request.setAttribute("message", status.getMessage());
            request.getServletContext().getRequestDispatcher("/status").forward(request, response);
        }
    }
}
