<div id="content">
    <h2 class="header">Frequently Asked Questions</h2>
    <hr>
    <div id="accordion">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Kostet die Benutzung des Services etwas?
                    </button>
                </h5>
            </div>
            <div id="collapseOne" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
                    Nein, unser Service ist komplett kostenlos!
                </div>
            </div>
        </div>
        <hr>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Wie viele Mitglieder sind pro WG m&ouml;glich ?
                    </button>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                    Prinzipiell sind unendlich viele Mitglieder m&ouml;glich, aber zur &Uuml;bersicht beschr&auml;nken wir die WG's auf 10 Personen!
                </div>
            </div>
        </div>
        <hr>
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