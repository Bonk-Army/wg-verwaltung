package logic;

import beans.LoginBean;
import utilities.ErrorCodes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();

        String email = request.getParameter("email");

        ErrorCodes status = bean.sendPasswordResetLink(email);

        switch(status){
            case SUCCESS:
                // Show success page
                request.getServletContext().getRequestDispatcher("/de/login/reset-success.jsp").forward(request, response);
                break;
            case WRONGEMAIL:
                // Show wrong email page
                request.getServletContext().getRequestDispatcher("/de/login/reset-wrong-credentials.jsp").forward(request, response);
                break;
            case FAILURE:
                // Show server error page
                request.getServletContext().getRequestDispatcher("/de/login/reset-server-error.jsp").forward(request, response);
                break;
        }
    }
}
