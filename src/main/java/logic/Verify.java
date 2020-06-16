package logic;

import beans.LoginBean;
import utilities.ErrorCodes;
import utilities.RegexHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Verify extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

        switch (status) {
            case SUCCESS:
                // Show success page
                request.getServletContext().getRequestDispatcher("/responseSuccess").forward(request, response);
                break;
            case FAILURE:
                // Show failure page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
