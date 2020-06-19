<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"></jsp:useBean>
    <jsp:useBean id="todoBean" class="beans.ToDoBean" scope="request"></jsp:useBean>
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
        <c:forEach var="i" begin="1" end="5">
        <tr class="<jsp:getProperty name="sessionBean" property="userId"/>">
            <td>
                <form action="removeLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="<jsp:getProperty name="sessionBean" property="userId"/>">

                    <button title="ToDo remove check" onclick="removeTodo(<jsp:getProperty name="sessionBean" property="userId"/>)"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal" data-target="#removeModal"
                            <jsp:getProperty name="sessionBean" property="userId"/>
                    >&times;
                    </button>
                    <button title="ToDo remove check" id="remove<jsp:getProperty name="sessionBean" property="userId"/>" type="submit"
                            style="display: none;"></button>
                </form>
            </td>
            <td>
                <jsp:getProperty name="todoBean" property="username"/>
            </td>
            <td>
                <jsp:getProperty name="sessionBean" property="userId"/>
            </td>
            <td>
                <jsp:getProperty name="sessionBean" property="userId"/>
            </td>
            <td>
                <jsp:getProperty name="sessionBean" property="userId"/>
            </td>
            <td>
                <jsp:getProperty name="sessionBean" property="userId"/>
            </td>
            <td>
                <form action="setDoneLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="<jsp:getProperty name="sessionBean" property="userId"/>">
                    <button title="ToDo check" onclick="doneTodo(<jsp:getProperty name="sessionBean" property="userId"/>)"
                            class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#todoModal"
                            <jsp:getProperty name="sessionBean" property="userId"/>
                    >erledigt?
                    </button>
                    <button title="ToDo check" id="<jsp:getProperty name="sessionBean" property="userId"/>" type="submit"
                            style="display: none;"></button>
                </form>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>