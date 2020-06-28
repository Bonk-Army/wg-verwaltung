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


        String url = request.getRequestURL().toString();

        String part = url.substring(url.lastIndexOf('/'));

        if (publicPages.contains(part)) {
            isPublicPage = true;
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
                if (!loginBean.isSessionCookieStillValid(userId)) {
                    ErrorCodes status = ErrorCodes.AUTHFAIL;
                    // Show an authentication error
                    request.setAttribute("isSadLlama", true);
                    request.setAttribute("header", status.getHeader());
                    request.setAttribute("message", status.getMessage());
                    request.getServletContext().getRequestDispatcher("/status").forward(request, response);
                } else {
                    // Set last login time for that user
                    loginBean.setLastLogin(userId);
                    // Forward user
                    sessionBean = new SessionBean(userId);
                    if (userId.equals("27")) {
                        int random = (int) (Math.random() * 5);
                        switch (random) {
                            case 1:
                                response.sendRedirect("https://www.youtube.com/watch?v=8KsT6RgXF_I");
                                break;
                            case 2:
                                response.sendRedirect("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
                                break;
                            case 3:
                                response.sendRedirect("https://www.youtube.com/watch?v=LZ3TlUQEvTY");
                                break;
                            case 4:
                                response.sendRedirect("https://youtu.be/GBww5jEWC4o");
                                break;
                            default:
                                response.sendRedirect("https://www.youtube.com/watch?v=YNDVipmJfz8");
                                break;
                        }
                    } else {
                        request.getSession().setAttribute("sessionBean", sessionBean);
                        request.getServletContext().getRequestDispatcher((part + "Page")).forward(request, response);
                    }
                }
            } else {
                // If the page is a public page, the user can be forwarded to that page even if he is not logged in.
                // If it is a page in the protected area, he will be redirected to the login page if he is not logged in
                if (isPublicPage) {
                    request.getServletContext().getRequestDispatcher(part + "Page").forward(request, response);
                } else {
                    response.sendRedirect("/");
                }
            }
        } else {
            // Session Bean available, forward user
            request.getServletContext().getRequestDispatcher(part + "Page").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
