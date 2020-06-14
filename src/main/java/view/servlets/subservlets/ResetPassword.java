package view.servlets.subservlets;

import view.old.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResetPassword extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        head.setPageName("Reset Password Page");
        head.addCSS("ResetPassword", "css");
        head.addContentPart(new TemplateFromPath("Import", "Global", "html"));
        head.addContentPart(new TemplateFromPath("Import", "Bootstrap", "html"));

        String username = request.getParameter("uname");
        String key = request.getParameter("key");

        body.addContentPart(new view.old.parts.ContentSubparts.ResetPassword(username, key));

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
