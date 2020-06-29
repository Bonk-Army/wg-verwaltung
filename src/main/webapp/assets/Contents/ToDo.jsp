<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<jsp:useBean id="todoBean" class="beans.ToDoBean" scope="request"/>
<jsp:setProperty name="todoBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="todoBean" property="wgId" value="${sessionBean.wgId}"/>
<div id="content">
    <h2 class="header">Alle ToDos in der WG ${sessionBean.wgName} &#129433;</h2>
    <h5>Deine WG hat noch ${todoBean.openTodosWg} offene ToDos, davon sind ${todoBean.openTodosUser} dir zugeordnet.</h5>
    <button title="ToDo hinzuf&uuml;gen" id="addToDo" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#createToDo">+
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col"/>
            <th scope="col">Aufgabe</th>
            <th scope="col">zu erledigen bis</th>
            <th scope="col">wird erledigt von</th>
            <th scope="col">Erledigt?</th>
            <th scope="col">Check</th>
            <th scope="col"/>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todoBean.todos}" var="todo">
        <tr class="${todo.colorClass}">
            <td>
                <a class="info" data-toggle="popover" data-trigger="hover" data-placement="right"
                   data-content="Dieses ToDo wurde am ${todo.dateCreated} von ${todo.creator} hinzugef&uuml;gt."
                   data-original-title="ToDo Info"><i class="fas fa-info-circle"></i></a>
            </td>
            <td>${todo.task}</td>
            <td>${todo.dateDue}</td>
            <td>${todo.assignee}</td>
            <td>${todo.doneMessage}</td>
            <td>
                <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">
                <button title="ToDo check" onclick="doneTodo(${todo.todoId})" class="btn btn-lg btn-primary btn-block" type="button"
                        data-toggle="modal" data-target="#doneModal" ${todo.buttonHideStatus}>erledigt?
                </button>
                <a href="/setDoneLogic?todoId=${todo.todoId}&isMyTodo=false" id="done${todo.todoId}" style="display: none;"></a>
            </td>
            <td>
                <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">

                <button title="ToDo remove check" onclick="removeTodo(${todo.todoId})"
                        class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                        data-target="#removeModal" ${todo.buttonHideStatus}>&times;
                </button>
                <a href="/removeTodoLogic?todoId=${todo.todoId}&isMyTodo=false" id="remove${todo.todoId}" style="display: none;"></a>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>