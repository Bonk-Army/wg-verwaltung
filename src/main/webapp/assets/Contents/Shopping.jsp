<div id="content">
    <h2 class="header">Einkaufsliste der WG ${sessionBean.wgName} &#129433;</h2>
    <button title="Artikel hinzuf&uuml;gen" id="addShopping" class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal"
            data-target="#exampleModal">+
    </button>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col"/>
            <th scope="col">Artikel</th>
            <th scope="col">Menge</th>
            <th scope="col">zu kaufen bis</th>
            <th scope="col">gew&uuml;nscht von</th>
            <th scope="col">Check</th>
            <th scope="col"/>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${shoppingBean.requests}" var="article">
        <tr class="${article.colorClass}">
            <td>
                <a class="info" data-toggle="popover" data-trigger="hover" data-placement="right"
                   data-content="Dieser Artikel wurde am ${article.dateCreated} von ${article.createdBy} hinzugef&uuml;gt."
                   data-original-title="Artikel Info"><i class="fas fa-info-circle"></i></a>
            </td>
            <td>${article.article}</td>
            <td>${article.amount}</td>
            <td>${article.dateDue}</td>
            <td>${article.requestedBy}</td>
            <td>
                <button title="Shopping check" onclick="boughtArticle(${article.requestId})" class="btn btn-lg btn-primary btn-block"
                        type="button"
                        data-toggle="modal" data-target="#todoModal" ${article.buttonHideStatus}>erledigt?
                </button>
                <a href="/setBoughtLogic?requestId=${article.requestId}" id="bought${article.requestId}"
                   style="display: none;"></a>
            </td>
            <td>
                <button title="ToDo remove check" onclick="removeArticle(${article.requestId})"
                        class="btn btn-lg btn-primary btn-block remove" type="button" data-toggle="modal"
                        data-target="#removeModal" ${article.buttonHideStatus}>&times;
                </button>
                <a href="/removeShoppingRequestLogic?requestId=${article.requestId}" id="remove${article.requestId}"
                   style="display: none;"></a>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
</div>