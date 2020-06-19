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
    public Reset() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //LoginBean bean = new LoginBean();
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/changePassword");
        //dispatcher.forward(request, response);
        //DEPRECATED???
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
                request.getServletContext().getRequestDispatcher("/responseSuccess").forward(request, response);
                break;
            case WRONGEMAIL:
                // Show wrong email page
                request.getServletContext().getRequestDispatcher("/responseWrongEMail").forward(request, response);
                break;
            case FAILURE:
                // Show server error page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }
}
