package filter;

import beans.LoginBean;
import beans.SessionBean;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURL().toString();

        String part = url.substring(url.lastIndexOf('/'));

        // Check if session bean already exists
        SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");

        // If no session bean has been detected, continue with the logic. If there is a session bean, just forward the request
        if(sessionBean == null || sessionBean.getUserId().equals("")){
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
            if(!userId.isEmpty()) {
                sessionBean = new SessionBean(userId);
                req.getSession().setAttribute("sessionBean", sessionBean);
                req.getServletContext().getRequestDispatcher((part + "Page")).forward(req, resp);
            } else {
                resp.sendRedirect("/");
            }
        } else {
            req.getServletContext().getRequestDispatcher(part + "Page").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
