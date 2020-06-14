package logic;

import beans.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Verify extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Verify() {
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
        String username = request.getParameter("uname");

        //If verification was successful, show success message. Otherwise show error.
        if (bean.verifyUser(username, verificationCode)) {
            //head.setPageName("Success!");
            //TODO Change this
            //body.addContentPart(new Login());
        } else {
            //head.setPageName("Failure!");
            //TODO Change this
            //body.addContentPart(new Login());
        }

        //<head>
        //    <meta http-equiv="Refresh" content="0; URL=https://wgverwaltung.azurewebsites.net/login">
        //</head>

        PrintWriter out = response.getWriter();
        //out.write(html.generateThisPart());
        //this.html.clear();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
