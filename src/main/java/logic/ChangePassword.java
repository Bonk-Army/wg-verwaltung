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
 * Called when the users tries to change his password from the settings page
 */
public class ChangePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangePassword() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user enters his email address to receive a link to reset his password
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingsBean settingsBean = new SettingsBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String username = sessionBean.getUsername();

        ErrorCodes status = settingsBean.changePassword(username, oldPassword, newPassword);

        switch (status) {
            case SUCCESS:
                // Show success pages
                response.sendRedirect("/settings");
                break;
            case WRONGPASSWORD:
                // Show wrong email page
                request.getServletContext().getRequestDispatcher("/responseWrongPassword").forward(request, response);
                break;
            case FAILURE:
                // Show server error page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }
}
