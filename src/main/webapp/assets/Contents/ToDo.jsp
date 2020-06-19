<%@ page import="models.TodoModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
                out.print("<th/>");
                out.print("<th scope=\"col\">Aufgabe</th>");
                out.print("<th scope=\"col\">Zu erledigen bis:</th>");
                out.print("<th scope=\"col\">Erstellt von:</th>");
                out.print("<th scope=\"col\">Wird Erledigt von:</th>");
                out.print("<th scope=\"col\">Erledigt?</th>");
                out.print("<th scope=\"col\">Check</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");

                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if ((cookies[i].getName().compareTo("session")) == 0) {
                            value = cookies[i].getValue();
                            ToDo = (ArrayList<TodoModel>) fail.getAllTodosBySessionIdentifier(value);
                            for (TodoModel item : ToDo) {
                                String addClass;
                                String done;
                                String hidden;
                                Date currentDate = new Date();
                                Calendar c = Calendar.getInstance();
                                c.setTime(currentDate);
                                c.add(Calendar.DATE, 3);
                                Date threeDaysDate = c.getTime();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd. MMMM yyyy");

                                if (item.getDone()) {
                                    addClass = (item.getIsActive())? "done" : "done inactive";
                                    done = "ja";
                                    hidden = "hidden=\"hidden\"";
                                } else {
                                    done = "nein";
                                    hidden = "";
                                    if (currentDate.after(item.getDateDue())) {
                                        addClass = (item.getIsActive())? "notDone tooLate" : "notDone tooLate inactive";
                                    } else if (threeDaysDate.after(item.getDateDue())) {
                                        addClass = (item.getIsActive())? "notDone late" : "notDone late inactive";
                                    } else {
                                        addClass = (item.getIsActive())? "notDone" : "notDone inactive";
                                    }
                                }
                                out.print("<tr class=\"" + addClass + "\">");
                                out.print("<td>");
                                out.print("<form action=\"removeLogic\" method=\"POST\">");
                                out.print("<input type=\"text\" name=\"todoId\" hidden=\"hidden\" value=\"" + item.getUniqueID() + "\">");
                                out.print("<button title=\"ToDo remove check\" onclick=\"removeTodo(" + item.getUniqueID()
                                        + ")\" class=\"btn btn-lg btn-primary btn-block remove\" type=\"button\" data-toggle=\"modal\" data-target=\"#removeModal\" "
                                        + hidden + ">&times;</button>");
                                out.print("<button title=\"ToDo remove check\" id=\"remove" + item.getUniqueID()
                                        + "\" type=\"submit\" style=\"display: none;\"</button>");
                                out.print("</form>");
                                out.print("</td>");
                                out.print("<td>" + item.getTask() + " </td>");
                                out.print("<td>" + simpleDateFormat.format(item.getDateDue()) + " </td>");
                                out.print("<td>" + fail.getNameString(item.getCreatorUsername()) + " </td>");
                                out.print("<td>"+ fail.getNameString(item.getAssigneeUsername()) + " </td>");
                                out.print("<td >"+done+"</td>");
                                out.print("<td>");
                                out.print("<form action=\"setDoneLogic\" method=\"POST\">");
                                out.print("<input type=\"text\" name=\"todoId\" hidden=\"hidden\" value=\""+item.getUniqueID()+"\">");
                                out.print("<button title=\"ToDo check\" onclick=\"doneTodo("+item.getUniqueID()+")\" class=\"btn btn-lg btn-primary btn-block\" type=\"button\" data-toggle=\"modal\" data-target=\"#todoModal\" "+hidden+">erledigt?</button>");
                                out.print("<button title=\"ToDo check\" id=\""+item.getUniqueID()+"\" type=\"submit\" style=\"display: none;\"</button>");
                                out.print("</form>");
                                out.print("</td>");
                                out.print("</tr>");
                            }
                        }
                    }
                } else {
                    out.println("<h2>No cookies founds</h2>");
                }
                out.println("</tbody>");
                out.println("</table>");
            %>
</div>