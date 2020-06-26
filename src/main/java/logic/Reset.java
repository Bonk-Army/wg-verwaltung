package logic;

import beans.LoginBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Reset servlet that is called  when the user wants to reset his password and therefore entered his email address
 * to receive a link to reset his password
 */
public class Reset extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Reset() {
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
        LoginBean bean = new LoginBean();
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        ErrorCodes status = bean.sendPasswordResetLink(email);

        switch (status) {
            case SUCCESS:
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
