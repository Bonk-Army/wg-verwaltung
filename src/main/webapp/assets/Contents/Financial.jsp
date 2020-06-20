<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="financialBean" class="beans.FinancialBean" scope="request"/>
    <jsp:setProperty name="financialBean" property="userId" value="${sessionBean.userId}"/>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <c:forEach items="${financialBean.totalPerUser}" var="user">
                <th scope="col">${user.name}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${financialBean.totalPerUser}" var="user">
                <td>${user.sum}</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
    <button title="ToDo hinzuf&uuml;gen" id="addToDo" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#createFinancial">+
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th/>
            <th scope="col">Wann?</th>
            <th scope="col">Grund</th>
            <th scope="col">Betrag</th>
            <th scope="col">von wem?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${financialBean.getEntries(20)}" var="expense">
        <tr class="${expense.colorClass}">
            <td>
                <form action="removeFinancialEntryLogic" method="POST">
                    <input type="text" name="entryId" hidden="hidden" value="${expense.entryId}">

                    <button title="Expense remove check" onclick="removeExpense(${expense.entryId})"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                            data-target="#removeModal" ${expense.buttonHideStatus}>&times;
                    </button>
                    <button title="Expense remove check" id="remove${expense.entryId}" type="submit" style="display: none;"></button>
                </form>
            </td>
            <td>${expense.dateCreated}</td>
            <td>${expense.reason}</td>
            <td>${expense.value}</td>
            <td>${expense.createdBy}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>