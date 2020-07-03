package filter;

import beans.LoginBean;
import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet that is mapped before every content page to check if the user is already authenticated.
 */
public class MainFilter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> publicPages = Arrays.asList("/impressum", "/faq", "/contact");
        boolean isPublicPage = false;
        boolean isLoginPage = false;

        String url = request.getRequestURL().toString();

        String part = url.substring(url.lastIndexOf('/'));

        // If the user opens a public page, set isPublicPage to true
        if (publicPages.contains(part)) {
            isPublicPage = true;
        }

        // If the user opens the login page, set isLoginPage to true so we can redirect him to /home
        // if he is already logged in
        if (part.equals("/")) {
            isLoginPage = true;
        }

        // Check if session bean already exists
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

        // If no session bean has been detected, continue with the logic. If there is a session bean, just forward the request
        if (sessionBean == null || sessionBean.getUserId().equals("")) {
            // Authentication via session cookie
            Cookie[] cookies = request.getCookies();
            String sessionIdentifier = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session")) {
                    sessionIdentifier = cookie.getValue();
                }
            }

            LoginBean loginBean = new LoginBean();

            String userId = loginBean.getUserIdBySessionIdentifier(sessionIdentifier);


            // If the user has been authenticated via cookie, forward the request. Otherwise redirect to login page
            if (!userId.isEmpty()) {
                // If the cookie that the user sends is not valid anymore, show him an authentication error
                if (!loginBean.isSessionCookieStillValid(userId)) {
                    /*
                      /$$$$$$                                        /$$
                     /$$__  $$                                     /$$$$
                    | $$  \__/  /$$$$$$   /$$$$$$$  /$$$$$$       |_  $$
                    | $$       |____  $$ /$$_____/ /$$__  $$        | $$
                    | $$        /$$$$$$$|  $$$$$$ | $$$$$$$$        | $$
                    | $$    $$ /$$__  $$ \____  $$| $$_____/        | $$
                    |  $$$$$$/|  $$$$$$$ /$$$$$$$/|  $$$$$$$       /$$$$$$
                     \______/  \_______/|_______/  \_______/      |______/

                    User is not identified

                    Session Bean does not exist, the session cookie that the user sent is invalid (too old)
                    User gets an error page
                     */
                    if (isLoginPage) {
                        request.getServletContext().getRequestDispatcher("/login").forward(request, response);
                    } else {
                        ErrorCodes status = ErrorCodes.AUTHFAIL;
                        // Show an authentication error
                        request.setAttribute("isSadLlama", true);
                        request.setAttribute("header", status.getHeader());
                        request.setAttribute("message", status.getMessage());
                        request.getServletContext().getRequestDispatcher("/status").forward(request, response);
                    }
                } else {
                    /*
                      /$$$$$$                                       /$$$$$$
                     /$$__  $$                                     /$$__  $$
                    | $$  \__/  /$$$$$$   /$$$$$$$  /$$$$$$       |__/  \ $$
                    | $$       |____  $$ /$$_____/ /$$__  $$        /$$$$$$/
                    | $$        /$$$$$$$|  $$$$$$ | $$$$$$$$       /$$____/
                    | $$    $$ /$$__  $$ \____  $$| $$_____/      | $$
                    |  $$$$$$/|  $$$$$$$ /$$$$$$$/|  $$$$$$$      | $$$$$$$$
                     \______/  \_______/|_______/  \_______/      |________/

                    User is identified via cookie

                    Session Bean does not exist, session cookie is valid
                    User gets the page he opened
                     */
                    // Set last login time for that user
                    loginBean.setLastLogin(userId);
                    // Forward user
                    sessionBean = new SessionBean(userId);
                    // If the user opened the login page, redirect him to /home
                    if (isLoginPage) {
                        response.sendRedirect("/home");
                    } else {
                        request.getSession().setAttribute("sessionBean", sessionBean);
                        request.getServletContext().getRequestDispatcher((part + "Page")).forward(request, response);
                    }
                }
            } else {
                /*
                  /$$$$$$                                       /$$$$$$
                 /$$__  $$                                     /$$__  $$
                | $$  \__/  /$$$$$$   /$$$$$$$  /$$$$$$       |__/  \ $$
                | $$       |____  $$ /$$_____/ /$$__  $$         /$$$$$/
                | $$        /$$$$$$$|  $$$$$$ | $$$$$$$$        |___  $$
                | $$    $$ /$$__  $$ \____  $$| $$_____/       /$$  \ $$
                |  $$$$$$/|  $$$$$$$ /$$$$$$$/|  $$$$$$$      |  $$$$$$/
                 \______/  \_______/|_______/  \_______/       \______/

                User is not identified

                Session Bean does not exist, user sent no session cookie or it is invalid (wrong session identifier)
                If the page is public, he sees the page. If it is protected, he gets redirected to the login page
                 */
                // If the page is a public page, the user can be forwarded to that page even if he is not logged in.
                // If it is a page in the protected area, he will be forwarded to the login page if he is not logged in
                if (isPublicPage) {
                    request.getServletContext().getRequestDispatcher(part + "Page").forward(request, response);
                } else {
                    request.getServletContext().getRequestDispatcher("/login").forward(request, response);
                }
            }
        } else {
            /*
              /$$$$$$                                      /$$   /$$
             /$$__  $$                                    | $$  | $$
            | $$  \__/  /$$$$$$   /$$$$$$$  /$$$$$$       | $$  | $$
            | $$       |____  $$ /$$_____/ /$$__  $$      | $$$$$$$$
            | $$        /$$$$$$$|  $$$$$$ | $$$$$$$$      |_____  $$
            | $$    $$ /$$__  $$ \____  $$| $$_____/            | $$
            |  $$$$$$/|  $$$$$$$ /$$$$$$$/|  $$$$$$$            | $$
             \______/  \_______/|_______/  \_______/            |__/

            User is identified via bean

            Session Bean exists
            User gets the page he opened or the /home page if he only opened /
             */
            // Session Bean available, forward user to home if he opened the login page or to the requested page if it
            // is not the login page
            if (isLoginPage) {
                response.sendRedirect("/home");
            } else {
                request.getServletContext().getRequestDispatcher(part + "Page").forward(request, response);
            }
        }
    }
}
