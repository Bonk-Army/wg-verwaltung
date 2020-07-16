package logic;

import beans.SessionBean;
import beans.SettingsBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Called when the user wants to leave his wg
 */
public class LeaveWG extends HttpServlet {
    public LeaveWG() {
        super();
    }

    /**
     * Called when the user wants to leave his wg via the settings page
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingsBean settingsBean = new SettingsBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

        request.setCharacterEncoding("UTF-8");

        String userId = sessionBean.getUserId();

        ErrorCodes status = settingsBean.leaveWg(userId);

        if (status == ErrorCodes.SUCCESS) {
            // Clear wg fields on sessionBean
            sessionBean.setWgName("");
            sessionBean.setWgId("");
            // Show success pages
            response.sendRedirect("/settings");
        } else {
            //Show failure
            request.setAttribute("isSadLlama", true);
            request.setAttribute("header", status.getHeader());
            request.setAttribute("message", status.getMessage());
            request.getServletContext().getRequestDispatcher("/status").forward(request, response);
        }
    }
}
