<%@ page import="models.TodoModel" %>
<%@ page import="java.util.ArrayList" %>
<div id="content">
    <jsp:useBean id="fail" class="beans.ToDoBean"></jsp:useBean>
    <button title="ToDo hinzuf&uuml;gen" id="addToDo" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
        +
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Aufgabe</th>
            <th scope="col">Name</th>
            <th scope="col">Datum</th>
            <th scope="col">Erstellt von:</th>
            <th scope="col">Wird Erledigt von:</th>
            <th scope="col">Check?</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%
                Cookie cookie = null;
                Cookie[] cookies = null;
                String value = "";
                // Get an array of Cookies associated with the this domain
                cookies = request.getCookies();
                ArrayList<TodoModel> ToDo = new ArrayList<TodoModel>();


                if( cookies != null ) {
                    out.println("<h2> Found Cookies Name and Value</h2>");
                    for (int i = 0; i < cookies.length; i++) {
                        cookie = cookies[i];
                        if ((cookies[i].getName().compareTo("JSESSIONID")) == 0){
                            value = cookies[i].getValue();
                            out.print(value);
//                            ToDo = (ArrayList<TodoModel>) fail.getAllTodosBySessionIdentifier(value);
                            out.print("Name : " + cookie.getName( ) + ", ");
                            out.print("Value: " + cookie.getValue( )+" <br/>");
                        }
                        out.print("Name : " + cookie.getName( ) + ", ");
                        out.print("Value: " + cookie.getValue( )+" <br/>");
                    }
                } else {
                    out.println("<h2>No cookies founds</h2>");
                } %>
            <%-- <th scope="row"><%= fail.getAllTodosBySessionIdentifier({cookie['session']}) %></th>
            <td><%= fail.username %></td>
            <td><%= fail.date %></td>
            <% if (fail.check) {%>
            <td>juppp</td>
            <% } else { %>
            <td>nope</td>
            <% } %>--%>

        </tr>
    </table>
</div>