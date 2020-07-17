<div id="content">
    <h2 class="header">Willkommen
        <jsp:getProperty name="sessionBean" property="firstName"/>
        <jsp:getProperty name="sessionBean" property="lastName"/>
        ,
        oder besser bekannt als
        <jsp:getProperty name="sessionBean" property="username"/>
        aus der WG
        <jsp:getProperty name="sessionBean" property="wgName"/>
        &#129433;</h2>
    <hr>
    <h4>Aktuelles Guthaben: ${overviewBean.expenseSum}&euro;</h4>

    <canvas id="myChart" width="auto" height="40"></canvas>

    <br>
    <hr>
    <br>
    <h4>Offene ToDos in deiner WG: ${overviewBean.openTodosWg}</h4>
    <h5>davon dir zugeordnet: ${overviewBean.openTodosUser}</h5>

    <canvas id="myChartTodo" width="auto" height="40"></canvas>

    <hr>
    <h5>Letzter Login: ${overviewBean.lastLogin}</h5>
</div>