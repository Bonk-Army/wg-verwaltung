package view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        readPart(out,"templates//htmlTop.html");
        readPart(out,"templates//head.html");
        out.println("<body>");
        out.println("<div class=\"wrapper\">");
        readPart(out,"templates//sidebar.html");
        out.println("<div id=\"content\">");
        readPart(out, "main/java/pages//testpage.html");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        readPart(out,"templates//htmlBottom.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    protected void readPart (PrintWriter out,String dateipfad){
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(getServletContext().getRealPath("/") + "WEB-INF//classes//"+dateipfad)));
            String line = in.readLine();
            while (!line.equals("")){
                out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (Exception e){
        }
    }
}

