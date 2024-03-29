package logic;

import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Called when the user wants to log out of his account
 */
public class Logout extends HttpServlet {
    public Logout() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

        // If session bean does not exist, it already got cleared and the user is logged out.
        // Just delete the cookie and redirect to login page
        if (sessionBean == null) {
            // Delete the session cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("session")) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

            response.sendRedirect("/");
        } else {
            // Log the user out of the session bean
            ErrorCodes status = sessionBean.logout();

            // Delete the session cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("session")) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

            if (status == ErrorCodes.SUCCESS) {
                //Redirect to login page
                response.sendRedirect("/");
            } else {
                //Show failure
                request.setAttribute("isSadLlama", true);
                request.setAttribute("header", status.getHeader());
                request.setAttribute("message", status.getMessage());
                request.getServletContext().getRequestDispatcher("/status").forward(request, response);
            }
        }
    }
}
