package logic.login;

import beans.LoginBean;
import view.parts.ContentSubparts.Login_Register;
import view.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VerifyServlet extends Servlet {
    private static final long serialVersionUID = 1L;

    public VerifyServlet() {
        super();
    }

    /**
     * Called when the user opens the link to verify his email address
     *
     * @param request  The http GET request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginBean bean = new LoginBean();

        String verificationCode = request.getParameter("key");
        String userID = request.getParameter("id");

        head.addContentPart(new TemplateFromPath("CustomHTMLElements", "head", "html"));

        //If verification was successful, show success message. Otherwise show error.
        if (bean.verifyUser(userID, verificationCode)) {
            head.setPageName("Success!");
            //TODO Change this to be a link back to the login form or smth
            body.addContentPart(new Login_Register());
        } else {
            head.setPageName("Failure!");
            //TODO Change this to be a link back to the login form or smth
            body.addContentPart(new Login_Register());
        }

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());
        this.html.clear();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
