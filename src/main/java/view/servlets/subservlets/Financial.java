package view.servlets.subservlets;

import view.parts.ContentSubparts.SideBar;
import view.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Financial extends Servlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        head.addContentPart(new TemplateFromPath("CustomHTMLElements","head","html"));

        body.addContentPart(new SideBar());
        body.addContentPart(new TemplateFromPath("Pages","Financial","html"));

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());
        this.html.clear();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
