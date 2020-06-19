<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="shoppingBean" class="beans.ShoppingBean" scope="request"/>
    <jsp:setProperty name="shoppingBean" property="userId" value="${sessionBean.userId}"/>
    <button title="Artikel hinzuf&uuml;gen" id="addShopping" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#exampleModal">+
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th/>
            <th scope="col">Artikel</th>
            <th scope="col">Menge</th>
            <th scope="col">zu kaufen bis</th>
            <th scope="col">gewünscht von</th>
            <th scope="col">Gekauft?</th>
            <th scope="col">Check</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${shoppingBean.article}" var="article">
        <tr class="${article.colorClass}">
            <td>
                <form action="removeArticleLogic" method="POST">
                    <input type="text" name="articleId" hidden="hidden" value="${article.articleId}">

                    <button title="ToDo remove check" onclick="removeArticle(${article.articleId})"
                            class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                            data-target="#removeModal" ${article.buttonHideStatus}>&times;
                    </button>
                    <button title="Shopping remove check" id="remove${article.articleId}" type="submit" style="display: none;"></button>
                </form>
            </td>
            <td title="erstellt am ${article.dateCreated} von ${article.creator}">${article.article}</td>
            <td>${article.dateDue}</td>
            <td>${article.assignee}</td>
            <td>${article.boughtMessage}</td>
            <td>
                <form action="removeArticleLogic" method="POST">
                    <input type="text" name="todoId" hidden="hidden" value="${article.articleId}">
                    <button title="Shopping check" onclick="boughtArticle(${article.articleId})" class="btn btn-lg btn-primary btn-block" type="button"
                            data-toggle="modal" data-target="#todoModal" ${article.buttonHideStatus}>erledigt?
                    </button>
                    <button title="Shopping check" id="${article.articleId}" type="submit" style="display: none;"></button>
                </form>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>