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
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if ((cookies[i].getName().compareTo("session")) == 0){
                            value = cookies[i].getValue();
                            ToDo = (ArrayList<TodoModel>) fail.getAllTodosBySessionIdentifier(value);
                            for (TodoModel item : ToDo)
                            {
                                out.print("<td>"+ item.getTask() + " </td");
                                out.print("<td>"+ item.getDateDue()+ " </td>");
                                out.print("<td>"+ item.getDateDue() + " </td>");
                                out.print("<td>"+ item.getDone() + " </td>");
                                out.print("<td"+ item.getDone() + " </td>");
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