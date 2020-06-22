<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="cleanBean" class="beans.CleanBean" scope="request"/>
    <jsp:setProperty name="cleanBean" property="userId" value="${sessionBean.userId}"/>
    <jsp:setProperty name="cleanBean" property="wgId" value="${sessionBean.wgId}"/>
    <button title="ToDo hinzuf&uuml;gen" id="addClean" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#createClean">+
    </button>
    <form action="saveCleanLogic" method="POST">
        <button title="save" id="saveClean" class="btn btn-lg btn-primary btn-block remove" type="submit"><i class="fas fa-save"></i>
        </button>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th/>
                <th scope="col">Aufgabe</th>
                <th scope="col">Montag</th>
                <th scope="col">Dienstag</th>
                <th scope="col">Mittwoch</th>
                <th scope="col">Donnerstag</th>
                <th scope="col">Freitag</th>
                <th scope="col">Samstag</th>
                <th scope="col">Sonntag</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cleanBean.cleanData}" var="clean">
                <tr class="${clean.colorClass}">
                    <td>
                        <input type="text" name="cleanId" hidden="hidden" value="${clean.cleanId}">
                        <button title="CLean remove check" onclick="removeClean(${clean.cleanId})"
                                class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                                data-target="#removeModal">&times;
                        </button>
                        <button title="ToDo remove check" id="remove${clean.cleanId}" type="button" style="display: none;"></button>
                    </td>
                    <td>${clean.title}</td>
                    <td>
                        <select id="monday" class="form-control" name="${clean.cleanId}-monday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="tuesday" class="form-control" name="${clean.cleanId}-tuesday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="wednesday" class="form-control" name="${clean.cleanId}-wednesday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="thursday" class="form-control" name="${clean.cleanId}-thursday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="friday" class="form-control" name="${clean.cleanId}-friday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="saturday" class="form-control" name="${clean.cleanId}-saturday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="sunday" class="form-control" name="${clean.cleanId}-sunday" required>
                            <c:forEach items="${cleanBean.usersOfWg}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>