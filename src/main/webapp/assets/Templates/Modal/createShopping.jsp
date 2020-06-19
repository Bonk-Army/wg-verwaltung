<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="createArticleBean" class="beans.ShoppingBean" scope="request"/>
<jsp:setProperty name="createArticleBean" property="userId" value="${sessionBean.userId}"/>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Neuen Artikel zur Einkaufsliste hinzuf&uuml;gen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Artikel eingeben
            </div>
            <form action="addTodoLogic" method="POST">
                <label for="todo" class="sr-only">Artikel</label>
                <input id="todo" class="form-control" placeholder="Artikel" name="todo" type="text" required>
                <label for="amount" class="sr-only">Menge</label>
                <input id="amount" class="form-control" placeholder="Wie viel brauchst du?" name="menge" type="text" required>
                <label for="deadline" class="sr-only">Deadline</label>
                <input id="deadline" class="form-control" placeholder="Bis wann brauchst du den Artikel?" name="deadline" type="date"
                       required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">Artikel hinzuf&uuml;gen</button>
                </div>
            </form>
        </div>
    </div>
</div>