package view.servlets.subservlets;

import view.parts.ContentSubparts.SideBar;
import view.parts.ContentSubparts.TemplateFromPath;
import view.servlets.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ToDo extends Servlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        head.setPageName("ToDo Page");
        head.addCSS("Login", "css");
        head.addCSS("Sidebar", "css");
        head.addContentPart(new TemplateFromPath("Import", "Global", "html"));
        head.addContentPart(new TemplateFromPath("Import", "Bootstrap", "html"));

        body.addContentPart(new TemplateFromPath("Component", "sidebar", "html"));
        body.addContentPart(new TemplateFromPath("Content", "ToDo", "html"));

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
