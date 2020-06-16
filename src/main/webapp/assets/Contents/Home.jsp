<div id="content">
    <jsp:useBean id="fail" class="beans.DemoBean"></jsp:useBean>
    <jsp:useBean id="overview" class="beans.OverviewBean"></jsp:useBean>
    <jsp:useBean id="login" class="beans.LoginBean"></jsp:useBean>
    <%
        Cookie cookie = null;
        Cookie[] cookies = null;
        String value = "";
        // Get an array of Cookies associated with the this domain
        cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookies[i].getName().compareTo("session")) == 0) {
                    value = cookies[i].getValue();
                    String id = login.getUserIdBySessionIdentifier(value);
                    out.print("<h1>Willkommen "+overview.getFullName(id)+", oder besser bekannt als "+overview.getUsernameById(id)+" aus der WG "+overview.getWgNameByUserId(id)+ "&#129433;</h1>");
                }
            }
        } else {
            out.println("<h2>No cookies founds</h2>");
        }
    %>
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