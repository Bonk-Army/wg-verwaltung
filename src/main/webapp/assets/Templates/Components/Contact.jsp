<div id="content">
    <section class="mb-4">
    <h2 class="header">Kontaktiere uns</h2>
    <p class="text-center w-responsive mx-auto mb-5">Hast du Fragen? Z&ouml;gere nicht, uns direkt zu kontaktieren. Unser Team wird dir in wenigen Stunden helfen</p>
    <div class="row">
        <div class="offset-md-4 col-md-4">
            <form id="contact-form" name="contact-form" action="sendContactRequestLogic" method="POST">
                <div class="row">
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <input type="text" id="name" name="name" class="form-control" value="${sessionBean.firstName} ${sessionBean.lastName}" required/>
                            <label for="name">Dein Name</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <input type="text" id="email" name="email" class="form-control" value="${sessionBean.email}" required/>
                            <label for="email">Deine E-Mail</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="md-form mb-0">
                            <input type="text" id="subject" name="subject" class="form-control" required/>
                            <label for="subject">Betreff</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">

                        <div class="md-form">
                            <textarea type="text" id="message" name="message" rows="2" class="form-control md-textarea" required></textarea>
                            <label for="message">Deine Nachricht</label>
                        </div>

                    </div>
                </div>
            <div class="text-center">
                <button class="btn btn-primary" type="submit">Senden</button>
            </div>
            </form>
            <c:if test="${!sessionBean.loggedIn}">
                <div class="center">
                    <hr>
                </div>
                <div class="text-center">
                    <a href="./">Zur&uuml;ck zur Login-Seite</a>
                </div>
            </c:if>
            <div class="status"></div>
        </div>
    </div>

</section>
</div>