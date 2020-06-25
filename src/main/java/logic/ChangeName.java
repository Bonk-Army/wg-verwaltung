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
 * Called when the user tries to change his first and/or last name from the settings page
 */
public class ChangeName extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeName() {
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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userId = sessionBean.getUserId();

        ErrorCodes status = settingsBean.changeName(userId, firstName, lastName);

        switch (status) {
            case SUCCESS:
                // Update fields on sessionBean
                sessionBean.setFirstName(firstName);
                sessionBean.setLastName(lastName);
                // Show success page
                response.sendRedirect("/settings");
                break;
            case WRONGENTRY:
                // Show wrong email page
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
            case FAILURE:
                // Show server error page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }
}
