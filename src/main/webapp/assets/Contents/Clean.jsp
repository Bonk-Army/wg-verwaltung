<div id="content">
    <h2 class="header">Putzplan der WG ${sessionBean.wgName} &#129433;</h2>
    <button title="ToDo hinzuf&uuml;gen" id="addClean" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#createClean">+
    </button>
    <form action="updateCleaningLogic" method="POST">
        <button title="save" id="saveClean" class="btn btn-lg btn-primary btn-block remove" type="submit"><i class="fas fa-save"></i>
        </button>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Aufgabe</th>
                <th scope="col">Montag</th>
                <th scope="col">Dienstag</th>
                <th scope="col">Mittwoch</th>
                <th scope="col">Donnerstag</th>
                <th scope="col">Freitag</th>
                <th scope="col">Samstag</th>
                <th scope="col">Sonntag</th>
                <th/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cleanBean.cleanData}" var="clean">
                <tr>
                    <td>${clean.general.get(0).taskname}</td>
                    <td>
                        <select id="monday${clean.general.get(0).taskId}" class="form-control" name="${clean.general.get(0).taskId}-monday"
                                onchange="changeMe('monday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.monday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="tuesday${clean.general.get(0).taskId}" class="form-control"
                                name="${clean.general.get(0).taskId}-tuesday" onchange="changeMe('tuesday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.tuesday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="wednesday${clean.general.get(0).taskId}" class="form-control"
                                name="${clean.general.get(0).taskId}-wednesday"
                                onchange="changeMe('wednesday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.wednesday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="thursday${clean.general.get(0).taskId}" class="form-control"
                                name="${clean.general.get(0).taskId}-thursday"
                                onchange="changeMe('thursday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.thursday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="friday${clean.general.get(0).taskId}" class="form-control" name="${clean.general.get(0).taskId}-friday"
                                onchange="changeMe('friday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.friday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="saturday${clean.general.get(0).taskId}" class="form-control"
                                name="${clean.general.get(0).taskId}-saturday"
                                onchange="changeMe('saturday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.saturday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select id="sunday${clean.general.get(0).taskId}" class="form-control" name="${clean.general.get(0).taskId}-sunday"
                                onchange="changeMe('sunday${clean.general.get(0).taskId}')">
                            <c:forEach items="${clean.sunday}" var="user">
                                <option value="${user.userId}">${user.nameString}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="text" name="cleanId" hidden="hidden" value="${clean.general.get(0).taskId}">
                        <button title="CLean remove check" onclick="removeClean(${clean.general.get(0).taskId})"
                                class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                                data-target="#removeModal">&times;
                        </button>
                        <a href="/removeCleaningLogic?taskId=${clean.general.get(0).taskId}" id="remove${clean.general.get(0).taskId}"
                           style="display: none;"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>