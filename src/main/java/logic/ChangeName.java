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
    public ChangeName() {
        super();
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
                request.setAttribute("isSadLlama", false);
                request.setAttribute("header", status.getHeader());
                request.setAttribute("message", status.getMessage());
                request.getServletContext().getRequestDispatcher("/status").forward(request, response);
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
