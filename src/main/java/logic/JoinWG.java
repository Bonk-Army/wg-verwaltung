package logic;

import beans.LoginBean;
import beans.SessionBean;
import beans.SettingsBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Join wg servlet that is called when the user tries to join a wg via invite code or invite link
 */
public class JoinWG extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JoinWG() {
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
        LoginBean loginBean = new LoginBean();
        SettingsBean settingsBean = new SettingsBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        // If user is not logged in, redirect him to the login page
        if (sessionBean == null) {
            response.sendRedirect("/");
        } else {
            String wgCode = request.getParameter("wgcode");

            String userId = sessionBean.getUserId();

            ErrorCodes status = settingsBean.setWgId(userId, wgCode);

            switch (status) {
                case SUCCESS:
                    //Show success
                    // Change wgId and wgName in the Session Bean
                    sessionBean.setWgId(settingsBean.getWgIdFromUserId(userId));
                    sessionBean.setWgName(settingsBean.getWgNameFromUserID(userId));
                    response.sendRedirect("/settings");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
