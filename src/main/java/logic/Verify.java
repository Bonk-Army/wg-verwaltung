package logic;

import beans.LoginBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Verify servlet that is called when the user clicks the link to verify his email address that we sent him to the
 * email address he entered
 */
public class Verify extends HttpServlet {
    public Verify() {
        super();
    }

    /**
     * Called when the user opens the link to verify his email address
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();
        request.setCharacterEncoding("UTF-8");

        String verificationCode = request.getParameter("key");
        String username = request.getParameter("uname");

        //If verification was successful, show success message. Otherwise show error.
        ErrorCodes status = bean.verifyUser(username, verificationCode);

        if (status == ErrorCodes.SUCCESS) {
            // Show success page
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
