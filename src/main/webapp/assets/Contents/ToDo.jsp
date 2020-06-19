<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="todoBean" class="beans.ToDoBean" scope="request"/>
    <jsp:setProperty name="todoBean" property="userId" value="${sessionBean.userId}"/>
    <button title="ToDo hinzuf&uuml;gen" id="addToDo" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#exampleModal">+
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th/>
            <th scope="col">Aufgabe</th>
            <th scope="col">Zu erledigen bis:</th>
            <th scope="col">Erstellt von:</th>
            <th scope="col">Wird Erledigt von:</th>
            <th scope="col">Erledigt?</th>
            <th scope="col">Check</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${todos}" var="todo">
        <tr class="${todo.addClass}">
            <td>
                <form action="removeLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">

                    <button title="ToDo remove check" onclick="removeTodo(${todo.todoId})"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                            data-target="#removeModal" ${todo.hidden}>&times;
                    </button>
                    <button title="ToDo remove check" id="remove${todo.todoId}" type="submit" style="display: none;"></button>
                </form>
            </td>
            <td>${todo.task}</td>
            <td>${todo.date}</td>
            <td>${todo.creatorUsername}</td>
            <td>${todo.assigneeUsername}</td>
            <td>${todo.done}</td>
            <td>
                <form action="setDoneLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="${todo.todoId}">
                    <button title="ToDo check" onclick="doneTodo(${todo.todoId})" class="btn btn-lg btn-primary btn-block" type="button"
                            data-toggle="modal" data-target="#todoModal" ${todo.hidden}>erledigt?
                    </button>
                    <button title="ToDo check" id="${todo.todoId}" type="submit" style="display: none;"></button>
                </form>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>