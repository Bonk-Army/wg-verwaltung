package logic;

import beans.SettingsBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Contact Request Servlet that is called when the user sends the contact form data
 */
public class ContactRequest extends HttpServlet {
    public ContactRequest() {
        super();
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

        if (status == ErrorCodes.SUCCESS) {
            //Show success
            request.setAttribute("isSadLlama", false);
            request.setAttribute("header", status.getHeader());
            request.setAttribute("message", status.getMessage());
            request.getServletContext().getRequestDispatcher("/status").forward(request, response);
        } else {
            //Show failure
            request.setAttribute("isSadLlama", true);
            request.setAttribute("header", status.getHeader());
            request.setAttribute("message", status.getMessage());
            request.getServletContext().getRequestDispatcher("/status").forward(request, response);
        }
    }
}
