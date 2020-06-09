package view.servlets.subservlets;

import config.globalConfig;
import view.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends Servlet {

    public Login(){
        //Fuck lazy Initzialisation....
        globalConfig g = new globalConfig();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        head.setPageName("Login Page");
        head.setCssFilePath("./assets/styles/Login.css");
        head.addContentPart(new TemplateFromPath("CustomHTMLElements//Head","Global","html"));
        head.addContentPart(new TemplateFromPath("CustomHTMLElements//Head","Bootstrap","html"));

        body.addContentPart(new view.parts.ContentSubparts.Login());

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}