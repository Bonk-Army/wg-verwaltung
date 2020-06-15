<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <h1>Willkommen <%= fail.vorname%> <%= fail.nachname%>, oder besser bekannt als "<%= fail.username%>" aus der WG "<%=fail.wg%>"	&#129433;</h1>
    <hr>
    <h4>Aktuelles Guthaben: <%= fail.kontoguthaben%>&euro;</h4>
    <canvas id="myChart" width="auto" height="40"></canvas>
    <script>
        <%@include file="../../assets/Scripts/ChartJS_Guthaben.js" %>
    </script>
    <br>
    <hr>
    <br>
    <h4>Offene ToDo's: <%= fail.todo%></h4>
    <canvas id="myChartTodo" width="auto" height="40"></canvas>
    <script>
        <%@include file="../../assets/Scripts/ChartJS_ToDo.js" %>
    </script>
    <br>
    <hr>
    <br>
    Letzter Login <%= fail.getDate() %>
</div>