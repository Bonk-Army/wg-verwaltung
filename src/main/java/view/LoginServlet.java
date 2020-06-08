package view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginBean;

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
        LoginBean bean = new LoginBean();

        Boolean isRegister = false;
        if(request.getParameter("isRegister") != null) {
            isRegister = request.getParameter("isRegister").equals("on");
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = "";
        Boolean stayLoggedIn = false;   // This determines the lifetime of the cookie that we send to the user
                                        // (false = session cookie, true = cookie that lasts 30 days or so)

        if(isRegister){
            email = request.getParameter("email");
        }else{
            stayLoggedIn = Boolean.valueOf(request.getParameter("keepSignedIn"));
        }

        String userId = isRegister ? bean.register(username, password, email) : bean.login(username, password);

        if(!userId.isEmpty()){
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta tile='Success!");
            out.println("</head>");
            out.println("<body>");
            out.println("Successfully logged you in, your ID is " + userId);
            out.println("</body>");
            out.println("</html>");
        } else{
            //Login / Registration was not successfull, return error page
        }
    }
}
