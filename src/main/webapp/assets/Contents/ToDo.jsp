<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Aufgabe</th>
            <th scope="col">Name</th>
            <th scope="col">Datum</th>
            <th scope="col">Check?</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><%= fail.todo %></th>
            <td><%= fail.username %></td>
            <td><%= fail.date %></td>
            <% if (fail.check) {%>
            <td>juppp</td>
            <% } else { %>
            <td>nope</td>
            <% } %>
        </tr>
    </table>
</div>