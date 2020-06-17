package filter;

import beans.LoginBean;
import beans.SessionBean;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURL().toString();

        String part = url.substring(url.lastIndexOf('/'));

        //AUTHENTIFIZIERUNG
        Cookie[] cookies = req.getCookies();
        String sessionIdentifier = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                sessionIdentifier = cookie.getValue();
            }
        }

        String userId = new LoginBean().getUserIdBySessionIdentifier(sessionIdentifier);

        SessionBean sessionBean = new SessionBean(userId);

        req.getSession().setAttribute("sessionBean", sessionBean);

        req.getServletContext().getRequestDispatcher(part+"Page").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
