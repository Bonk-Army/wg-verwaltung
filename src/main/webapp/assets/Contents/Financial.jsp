<div id="content">
    <h2 class="header">Finanzielles der WG ${sessionBean.wgName} &#129433;</h2>
    <table class="table overview">
        <thead class="thead-dark">
        <tr>
            <c:forEach items="${financialBean.totalPerUser}" var="user">
                <th scope="col">${user.nameString}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${financialBean.totalPerUser}" var="user">
                <td>${user.sum}&euro;</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    <button title="ToDo hinzuf&uuml;gen" id="addArticle" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#createFinancial">+
    </button>
    <table class="table all">
        <thead class="thead-dark tasks">
        <tr>
            <th scope="col">Wann?</th>
            <th scope="col">Grund</th>
            <th scope="col">Betrag</th>
            <th scope="col">von wem?</th>
            <th/>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${financialBean.getEntries(20)}" var="expense">
            <tr class="${expense.colorClass}">
                <td>${expense.dateCreated}</td>
                <td>${expense.reason}</td>
                <td>${expense.value}&euro;</td>
                <td>${expense.createdBy}</td>
                <td>
                    <input type="text" name="entryId" hidden="hidden" value="${expense.entryId}">

                    <button title="Expense remove check" onclick="removeExpense(${expense.entryId})"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                            data-target="#removeModal">&times;
                    </button>
                    <a href="/removeFinancialEntryLogic?entryId=${expense.entryId}" id="remove${expense.entryId}"
                       style="display: none;"></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>