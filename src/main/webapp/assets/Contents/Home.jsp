<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <h1>Willkommen <%= fail.username%>
    </h1>
    <br>
    <h3>Dein momentaner WG-Status ist</h3>
    <h4>Name: <%=fail.vorname%> <%=fail.nachname%></h4>
    <p>Todos: <%= fail.todo%></p>
    <p>Guthaben: <%= fail.kontoguthaben%> $</p>
    <p>Datum: <%= fail.date %></p>
</div>