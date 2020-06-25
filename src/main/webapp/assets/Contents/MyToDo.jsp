<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="todoBean" class="beans.ToDoBean" scope="request"/>
    <jsp:setProperty name="todoBean" property="userId" value="${sessionBean.userId}"/>
    <jsp:setProperty name="todoBean" property="wgId" value="${sessionBean.wgId}"/>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Aufgabe</th>
            <th scope="col">zu erledigen bis</th>
            <th scope="col">wird erledigt von</th>
            <th scope="col">Erledigt?</th>
            <th scope="col">Check</th>
            <th/>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todoBean.todos}" var="todo">
        <tr class="${todo.colorClass}">
            <td title="erstellt am ${todo.dateCreated} von ${todo.creator}">${todo.task}</td>
            <td>${todo.dateDue}</td>
            <td>${todo.assignee}</td>
            <td>${todo.doneMessage}</td>
            <td>
                <form action="setDoneLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">
                    <button title="ToDo check" onclick="doneTodo(${todo.todoId})" class="btn btn-lg btn-primary btn-block" type="button"
                            data-toggle="modal" data-target="#doneModal" ${todo.buttonHideStatus}>erledigt?
                    </button>
                    <button title="ToDo check" id="${todo.todoId}" type="submit" style="display: none;"></button>
                </form>
            </td>
            <td>
                <form action="removeLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">

                    <button title="ToDo remove check" onclick="removeTodo(${todo.todoId})"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                            data-target="#removeModal" ${todo.buttonHideStatus}>&times;
                    </button>
                    <button title="ToDo remove check" id="remove${todo.todoId}" type="submit" style="display: none;"></button>
                </form>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>