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
 * Create wg servlet that is called when the user creates a new wg via form
 */
public class CreateWG extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateWG() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user tries to create a WG via settings page or invite link
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean loginBean = new LoginBean();
        SettingsBean settingsBean = new SettingsBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String wgName = request.getParameter("wgname");

        String userId = sessionBean.getUserId();

        ErrorCodes status = settingsBean.createWg(userId, wgName);

        switch (status) {
            case SUCCESS:
                // Change wgId and wgName in the Session Bean
                sessionBean.setWgId(settingsBean.getWgIdFromUserId(userId));
                sessionBean.setWgName(wgName);
                //Show success
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
