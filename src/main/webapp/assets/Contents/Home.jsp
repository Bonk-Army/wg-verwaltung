<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <jsp:useBean id="overview" class="beans.OverviewBean"></jsp:useBean>
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"></jsp:useBean>

    <h1>Willkommen
        <jsp:getProperty name="sessionBean" property="firstName"/>
        <jsp:getProperty name="sessionBean" property="lastName"/>
        , oder besser bekannt als
        <jsp:getProperty name="sessionBean" property="username"/>
        aus der WG
        <jsp:getProperty name="sessionBean" property="wgName"/>
        &#129433;</h1>
    <hr>
    <h4>Aktuelles Guthaben: <%= fail.kontoguthaben%>&euro;</h4>
    <canvas id="myChart" width="auto" height="40"></canvas>
    <script>
        <%@include file="../Scripts/ChartJS/ChartJS_Guthaben.js" %>
    </script>
    <br>
    <hr>
    <br>
    <h4>Offene ToDo's: <%= fail.todo%>
    </h4>
    <canvas id="myChartTodo" width="auto" height="40"></canvas>
    <script>
        <%@include file="../Scripts/ChartJS/ChartJS_ToDo.js" %>
    </script>
    <br>
    <hr>
    <br>
    Letzter Login <%= fail.getDate() %>
</div>