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
    private static final long serialVersionUID = 1L;

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

        switch (status) {
            case SUCCESS:
                // Clear wg fields on sessionBean
                sessionBean.setWgName("");
                sessionBean.setWgId("");
                // Show success pages
                response.sendRedirect("/settings");
                break;
            case FAILURE:
                // Show server error page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
