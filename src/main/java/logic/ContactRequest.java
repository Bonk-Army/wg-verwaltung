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

public class ContactRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactRequest() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user makes a contact request via the contact form
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingsBean settingsBean = new SettingsBean();
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        ErrorCodes status = settingsBean.sendContactMail(name, email, subject, message);

        switch (status) {
            case SUCCESS:
                //Show success
                request.getServletContext().getRequestDispatcher("/responseSuccess").forward(request, response);
                break;
            case FAILURE:
                //Show failure
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }
}
