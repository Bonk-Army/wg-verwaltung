package filter;

import beans.LoginBean;
import beans.SessionBean;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> publicPages = Arrays.asList("impressum", "faq", "contact");
        boolean isPublicPage = false;


        String url = req.getRequestURL().toString();

        String part = url.substring(url.lastIndexOf('/'));

        if (publicPages.contains(part)) {
            isPublicPage = true;
        }

        // Check if session bean already exists
        SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");

        // If no session bean has been detected, continue with the logic. If there is a session bean, just forward the request
        if (sessionBean == null || sessionBean.getUserId().equals("")) {
            // Authentication via session cookie
            Cookie[] cookies = req.getCookies();
            String sessionIdentifier = "";

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session")) {
                    sessionIdentifier = cookie.getValue();
                }
            }

            String userId = new LoginBean().getUserIdBySessionIdentifier(sessionIdentifier);

            // If the user has been authenticated via cookie, forward the request. Otherwise redirect to login page
            if (!userId.isEmpty()) {
                sessionBean = new SessionBean(userId);
                req.getSession().setAttribute("sessionBean", sessionBean);
                req.getServletContext().getRequestDispatcher((part + "Page")).forward(req, resp);
            } else {
                // If the page is a public page, the user can be forwarded to that page even if he is not logged in.
                // If it is a page in the protected area, he will be redirected to the login page if he is not logged in
                if (isPublicPage) {
                    req.getServletContext().getRequestDispatcher(part + "Page").forward(req, resp);
                } else {
                    resp.sendRedirect("/");
                }
            }
        } else {
            // Session Bean available, forward user
            req.getServletContext().getRequestDispatcher(part + "Page").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
