package view.servlets;

import view.parts.HTMLSubparts.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public abstract class Servlet extends HttpServlet {

    protected Head head = new Head();
    protected Body body = new Body();
    protected HTML html = new HTML(head,body);

    public Servlet(){
        super();
    }

    abstract protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    abstract protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
