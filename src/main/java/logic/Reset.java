package logic;

import beans.LoginBean;
import view.servlets.Servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Reset extends Servlet {
    public Reset(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/changePassword");
        dispatcher.forward(request, response);

        //TODO: @GUI-TEAM create page for changing password
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();

        String email = request.getParameter("email");
        bean.sendPasswordResetLink(email);

        //TODO: Update page view for user...
    }
}
