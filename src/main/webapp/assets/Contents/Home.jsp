<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="overview" class="beans.OverviewBean"/>
    <jsp:setProperty name="overview" property="userId" value="${sessionBean.userId}"/>
    <jsp:setProperty name="overview" property="wgId" value="${sessionBean.wgId}"/>

    <h1>Willkommen
        <jsp:getProperty name="sessionBean" property="firstName"/>
        <jsp:getProperty name="sessionBean" property="lastName"/>, oder besser bekannt als
        <jsp:getProperty name="sessionBean" property="username"/>
        aus der WG
        <jsp:getProperty name="sessionBean" property="wgName"/>
        &#129433;</h1>
    <hr>
    <h4>Aktuelles Guthaben: ${overview.expenseSum}&euro;</h4>
    <canvas id="myChart" width="auto" height="40"></canvas>
    <br>
    <hr>
    <br>
    <h4>Offene ToDo's in deiner WG: ${overview.openTodosWg}</h4>
    <h5>davon dir zugeordnet: ${overview.openTodosUser}</h5>

    <canvas id="myChartTodo" width="auto" height="40"></canvas>
</div>