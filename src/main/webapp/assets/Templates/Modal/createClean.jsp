<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="createCleanBean" class="beans.CleanBean" scope="request"/>
<jsp:setProperty name="createCleanBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="createCleanBean" property="wgId" value="${sessionBean.wgId}"/>
<!-- Modal -->
<div class="modal fade" id="createClean" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Neue Ausgabe erstellen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Neue Aufgabe eingeben
            </div>
            <form action="addCleaningLogic" method="POST">
                <label for="exercise" class="sr-only">Aufgabe</label>
                <input id="exercise" class="form-control" placeholder="Aufgabe" name="taskname" type="text" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">Aufgabe hinzuf&uuml;gen</button>
                </div>
            </form>
        </div>
    </div>
</div>
