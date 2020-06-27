<div id="content">
    <h2 class="header">Frequently Asked Questions</h2>
    <hr>
    <div id="accordion">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0">
                    <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Mag ich Lamas?
                    </button>
                </h5>
            </div>
            <div id="collapseOne" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
                    Ja klar!
                </div>
            </div>
        </div>
        <hr>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Mag ich sie noch mehr?
                    </button>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                    &#129433;
                </div>
            </div>
        </div>
        <hr>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h5 class="mb-0">
                    <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Mag ich sie ganz ganz doll?
                    </button>
                </h5>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                <div class="card-body">
                    <a href="http://paul-wolf.de">Lama Hasser :(</a>
                    &#129433; &#129433; &#129433; &#129433; &emsp;&emsp;&emsp;&emsp;
                    <img src="https://www.dreisamtal.de/eip/clips/lightbox_3-alpakas.jpg?fl=30553981" class="rounded-circle"/>
                    &emsp;&emsp;&emsp;&emsp; &#129433; &#129433; &#129433; &#129433;
                    <a href="https://www.pfalz-lamas.de/">Gute Quellen..</a>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${!sessionBean.loggedIn}">
    <div class="center">
        <hr>
    </div>
    <div class="text-center">
        <a href="./">Zur&uuml;ck zu Login</a>
    </div>
    </c:if>
</div>