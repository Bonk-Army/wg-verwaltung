package logic;

import beans.LoginBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            // TODO Check for bad parsed characters (ÄÖÜ) (Ticket WGV-96)
            email = request.getParameter("email");
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
        }

        stayLoggedIn = Boolean.valueOf(request.getParameter("keepSignedIn"));

        ErrorCodes status = isRegister ? bean.register(username, password, email, firstName, lastName) : bean.login(username, password);

        //UserId required for session cookie
        String userId = "";
        switch (status) {
            case SUCCESS:
                // Log the user in, save a cookie and redirect him to the home page
                userId = bean.getUserId(username);
                String sessionIdentifier = bean.getSessionIdentifier(username);
                Cookie sessionCookie = new Cookie("session", (sessionIdentifier));
                int cookieAge = stayLoggedIn ? 2592000 : -1;
                // If user wants to stay logged in, make it live 30 days (2592000 seconds),
                // otherwise let it be a session cookie
                sessionCookie.setMaxAge(cookieAge);
                response.addCookie(sessionCookie);
                response.sendRedirect("/home");
                break;
            case WRONGENTRY:
                // Return "wrong entry" error page
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
            case WRONGUNAME:
                // Return "wrong entry" error page
                request.getServletContext().getRequestDispatcher("/responseWrongUName").forward(request, response);
                break;
            case FAILURE:
                // Return "try again" error page
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
        }
    }
}
