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
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        ErrorCodes status = bean.sendPasswordResetLink(email);

        switch(status){
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
