package logic;

import beans.LoginBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Reset complete servlet that is called after the user clicked the reset password link and now sends his new password
 */
public class ResetComplete extends HttpServlet {
    public ResetComplete() {
        super();
    }

    /**
     * Called when the user sends his new password and therefore completes the password reset process
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();
        request.setCharacterEncoding("UTF-8");

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String passwordResetKey = request.getParameter("key");

        ErrorCodes status = bean.resetPassword(username, passwordResetKey, password);

        switch (status) {
            case SUCCESS:
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
