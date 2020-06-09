package view.servlets;

import view.parts.HTMLSubparts.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public abstract class Servlet extends HttpServlet {
    /**
     * We use an HTML Object in an Servlet to generate a fully functional HTML Output
     */
    protected Head head = new Head();
    protected Body body = new Body();
    protected HTML html = new HTML(head,body);

    /**
     * Constructor of Servlet
     */
    public Servlet(){
        super();
    }

    /**
     * Methode um die GET Requests zu bearbeiten
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    abstract protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Methode um die POST Requests zu bearbeiten
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    abstract protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
