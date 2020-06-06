package main.java.view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.beans.LoginBean;

/**
 * LoginServlet to handle user login. Gets login data from html form and returns success or failure page.
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    /**
     * Called when the html login form from index.jsp is sent to the url for this servlet.
     * @param request The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Call LoginBean with required Logic

        LoginBean bean = new LoginBean();

        Boolean isRegister = Boolean.valueOf(request.getParameter("isRegister"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = "";
        Boolean stayLoggedIn = false;

        if(isRegister){
            email = request.getParameter("email");
        }else{
            stayLoggedIn = Boolean.valueOf(request.getParameter("keepSignedIn"));
        }
    }
}
