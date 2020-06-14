package logic;

import beans.LoginBean;
import view.parts.ContentSubparts.*;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends Servlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the html login form from index.jsp is sent to the url for this servlet.
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();

        Boolean isRegister = false;
        if (request.getParameter("isRegister") != null) {
            isRegister = request.getParameter("isRegister").equals("on");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = "";
        Boolean stayLoggedIn = false;   // This determines the lifetime of the cookie that we send to the user
        // (false = session cookie, true = cookie that lasts 30 days or so)

        if (isRegister) {
            email = request.getParameter("email");
        } else {
            stayLoggedIn = Boolean.valueOf(request.getParameter("keepSignedIn"));
        }

        String userId = isRegister ? bean.register(username, password, email) : bean.login(username, password);

        if(!userId.isEmpty()){
            //TODO forward to Homepage
        } else {
            // TODO Show error
        }
    }
}
