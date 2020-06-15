<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <h1>Willkommen <%= fail.vorname%> <%= fail.nachname%>, oder besser bekannt als "<%= fail.username%>" aus der WG "<%=fail.wg%>"	&#129433;</h1>
    <hr>
    <h4>Guthaben</h4>
    <canvas id="myChart" width="auto" height="50"></canvas>
    <script>
        <%@include file="../../assets/Scripts/ChartJS_Guthaben.js" %>
    </script>
    <br>
    <hr>
    <br>
    <h4>Offene ToDos: 2 </h4>
    <br>
    <hr>
    <br>
    Letzter Login <%= fail.getDate() %>
</div>