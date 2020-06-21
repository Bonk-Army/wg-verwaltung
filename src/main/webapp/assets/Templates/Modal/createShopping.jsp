<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="createArticleBean" class="beans.ShoppingBean" scope="request"/>
<jsp:setProperty name="createArticleBean" property="userId" value="${sessionBean.userId}"/>
<jsp:setProperty name="createArticleBean" property="username" value="${sessionBean.username}"/>
<jsp:setProperty name="createArticleBean" property="wgId" value="${sessionBean.wgId}"/>
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
            <form action="addShoppingRequestLogic" method="POST">
                <label for="article" class="sr-only">Artikel</label>
                <input id="article" class="form-control" placeholder="Artikel" name="article" type="text" required>
                <label for="amount" class="sr-only">Menge</label>
                <label for="amount" class="sr-only">Menge</label>
                <input id="amount" class="form-control" placeholder="Wie viel brauchst du?" name="amount" type="text" required>
                <h6>Bis wann brauchst du den Arikel?</h6>
                <label for="deadline" class="sr-only">Deadline</label>
                <input id="deadline" class="form-control" placeholder="Bis wann brauchst du den Artikel?" name="dateDue" type="date"
                       required>
                <label for="name" class="sr-only">Name</label>
                <h6>Wer m&ouml;chte diesen Artikel?</h6>
                <select id="name" class="form-control" name="requestedBy" required>
                    <c:forEach items="${createArticleBean.usersOfWg}" var="user">
                    <option value="${user.username}">${user.nameString}</option>
                    </c:forEach>
                </select>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">Artikel hinzuf&uuml;gen</button>
                </div>
            </form>
        </div>
    </div>
</div>