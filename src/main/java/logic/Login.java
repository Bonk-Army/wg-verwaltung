package logic;

import beans.LoginBean;
import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login Servlet that is called when the users tries to log in or register from the login page
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the html login form is sent to the url for this servlet.
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = new LoginBean();
        request.setCharacterEncoding("UTF-8");

        Boolean isRegister = false;
        if (request.getParameter("isRegister") != null) {
            isRegister = request.getParameter("isRegister").equals("on");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = "";
        String firstName = "";
        String lastName = "";
        Boolean stayLoggedIn = false;   // This determines the lifetime of the cookie that we send to the user
        // (false = session cookie, true = cookie that lasts 30 days or so)

        if (isRegister) {
            email = request.getParameter("email");
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
        }

        stayLoggedIn = Boolean.valueOf(request.getParameter("keepSignedIn"));

        int cookieLifetime = stayLoggedIn ? 30 : 0;

        ErrorCodes status = isRegister ? bean.register(username, password, email, firstName, lastName, cookieLifetime) : bean.login(username, password, cookieLifetime);

        //UserId required for session cookie
        String userId = "";
        switch (status) {
            case SUCCESS:
                // Log the user in, save a cookie, create a session bean and redirect him to the home page
                userId = bean.getUserId(username);
                String sessionIdentifier = (userId + "-" + bean.getCookiePostfixNotHashed());
                Cookie sessionCookie = new Cookie("session", (sessionIdentifier));
                int cookieAge = stayLoggedIn ? 2592000 : -1;
                // If user wants to stay logged in, make it live 30 days (2592000 seconds),
                // otherwise let it be a session cookie
                sessionCookie.setMaxAge(cookieAge);
                response.addCookie(sessionCookie);
                // Now create the session bean
                SessionBean sessionBean = new SessionBean(userId);
                request.getSession().setAttribute("sessionBean", sessionBean);
                response.sendRedirect("/home");
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
