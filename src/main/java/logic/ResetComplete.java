package logic;

import beans.LoginBean;
import utilities.ErrorCodes;
import utilities.RegexHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResetComplete extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String passwordResetKey =  request.getParameter("key");

        ErrorCodes status = bean.resetPassword(username, passwordResetKey, password);

        switch(status){
            case SUCCESS:
                request.getServletContext().getRequestDispatcher("/responseSuccess").forward(request, response);
                break;
            case FAILURE:
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
            case WRONGENTRY:
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
        }
    }
}
