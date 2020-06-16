<%@ page import="models.TodoModel" %>
<%@ page import="java.util.ArrayList" %>
<div id="content">
    <jsp:useBean id="fail" class="beans.ToDoBean"></jsp:useBean>
    <button title="ToDo hinzuf&uuml;gen" id="addToDo" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
        +
    </button>
            <%
                Cookie cookie = null;
                Cookie[] cookies = null;
                String value = "";
                // Get an array of Cookies associated with the this domain
                cookies = request.getCookies();
                ArrayList<TodoModel> ToDo = new ArrayList<TodoModel>();

                out.print("<table class=\"table\">");
                out.print("<thead class=\"thead-dark\">");
                out.print("<tr>");
                out.print("<th scope=\"col\">Aufgabe</th>");
                out.print("<th scope=\"col\">Zu erledigen bis:</th>");
                out.print("<th scope=\"col\">Erstellt von:</th>");
                out.print("<th scope=\"col\">Wird Erledigt von:</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                out.print("<tr>");

                if( cookies != null ) {
                    out.println("<h2> Found Cookies Name and Value</h2>");
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if ((cookies[i].getName().compareTo("session")) == 0){
                            value = cookies[i].getValue();
                            ToDo = (ArrayList<TodoModel>) fail.getAllTodosBySessionIdentifier(value);
                            out.print("Name : " + cookie.getName( ) + ", ");
                            out.print("Value: " + cookie.getValue( )+" <br/>");

                            for (TodoModel item : ToDo
                                 ) {
                                out.print("<tr>"+ item.getTask() + " </tr>");
                                out.print("<tr>"+ item.getDateDue()+ " </tr>");
                                out.print("<tr>"+ item.getDateDue() + " </tr>");
                                out.print("<tr>"+ item.getDone() + " </tr>");
                                out.print("<tr>"+ item.getDone() + " </tr>");
                            }
                        }
                    }
                } else {
                    out.println("<h2>No cookies founds</h2>");
                }
                out.println("</tr>");
                out.println("</table>");
            %>
</div>