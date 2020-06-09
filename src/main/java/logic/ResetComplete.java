package logic;

import beans.LoginBean;
import utilities.SQLDatabaseConnection;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResetComplete extends Servlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String passwordResetKey =  request.getParameter("key");

        if(bean.resetPassword(username, passwordResetKey, password)) {
            System.out.println("Ich bumse Deine Mutter.");
            //TODO
        } else {
            System.out.println("Ich bumse Deine Mutter nicht.");
            //TODO
        }
    }
}
