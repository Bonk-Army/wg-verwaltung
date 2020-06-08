package view.servlets.subservlets;

import config.globalConfig;
import view.servlets.Servlet;
import view.parts.ContentSubparts.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Content extends Servlet {

    public Content(){
        //Fuck lazy Initzialisation....
        globalConfig g = new globalConfig();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        head.addContentPart(new TemplateFromPath("CustomHTMLElements","head","html"));
        //head.setPageName("TestPage");

        body.addContentPart(new SideBar());
        body.addContentPart(new TemplateFromPath("Pages","testpage","html"));

        PrintWriter out = response.getWriter();
        out.write(html.generateThisPart());
        this.html.clear();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
