package logic;

import beans.LoginBean;
import beans.SettingsBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        request.setCharacterEncoding("UTF-8");

        String wgName = request.getParameter("wgname");

        Cookie[] cookies = request.getCookies();
        String sessionIdentifier = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                sessionIdentifier = cookie.getValue();
            }
        }

        String userId = loginBean.getUserIdBySessionIdentifier(sessionIdentifier);

        ErrorCodes status = settingsBean.createWg(userId, wgName);

        switch (status) {
            case SUCCESS:
                //Show success
                request.getServletContext().getRequestDispatcher("/responseSuccess").forward(request, response);
                break;
            case FAILURE:
                //Show failure
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
            case WRONGENTRY:
                //Show wrongentry
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
        }
    }
}
