package view.servlets.subservlets;

import config.globalConfig;
import view.parts.ContentSubparts.Login_Register;
import view.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login_RegisterServlet extends Servlet {

    public Login_RegisterServlet(){
        //Fuck lazy Initzialisation....
        globalConfig g = new globalConfig();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        head.setPageName("Login/Register Page");
        head.setCssFilePath("./assets/styles/Login/LoginRegister.css");
        head.addContentPart(new TemplateFromPath("CustomHTMLElements//Head","Global","html"));
        head.addContentPart(new TemplateFromPath("CustomHTMLElements//Head","Login_Register","html"));

        body.addContentPart(new Login_Register());

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
