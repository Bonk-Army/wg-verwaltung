<div id="content">
    <h2 class="header">Frequently Asked Questions</h2>
    <hr>
    <div id="accordion">
        <c:forEach items="${faqBean.faqs}" var="faq">
            <div class="card">
                <div class="card-header" id="heading${faq.number}">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${faq.number}" aria-expanded="true"
                                aria-controls="collapse${faq.number}">
                                ${faq.question}
                        </button>
                    </h5>
                </div>
                <div id="collapse${faq.number}" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                            ${faq.answer}
                    </div>
                </div>
            </div>
            <hr>
        </c:forEach>
    </div>
    <c:if test="${!sessionBean.loggedIn}">
    <div class="center">
        <hr>
    </div>
    <div class="text-center">
        <a href="./">Zur&uuml;ck zur Login-Seite</a>
    </div>
    </c:if>
</div>