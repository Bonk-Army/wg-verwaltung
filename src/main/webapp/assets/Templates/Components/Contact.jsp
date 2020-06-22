<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
<c:if test="${sessionBean.loggedIn}">
    <%@include file="Sidebar.jsp" %>
</c:if>
<div id="content">
    <section class="mb-4">

    <!--Section heading-->
    <h2 class="h1-responsive font-weight-bold text-center my-4">Kontaktiere uns</h2>
    <!--Section description-->
    <p class="text-center w-responsive mx-auto mb-5">Hast du Fragen? Z&ouml;gere nicht, uns direkt zu kontaktieren. Unser Team wird dir in wenigen Stunden helfen</p>
    <div class="row">
        <!--Grid column-->
        <div class="offset-md-4 col-md-4">
            <form id="contact-form" name="contact-form" action="sendContactRequestLogic" method="POST">
                <!--Grid row-->
                <div class="row">
                    <!--Grid column-->
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <input type="text" id="name" name="name" class="form-control" value="${sessionBean.firstName} ${sessionBean.lastName}">
                            <label for="name">Dein Name</label>
                        </div>
                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-md-6">
                        <div class="md-form mb-0">
                            <input type="text" id="email" name="email" class="form-control" value="${sessionBean.email}">
                            <label for="email">Deine E-Mail</label>
                        </div>
                    </div>
                    <!--Grid column-->

                </div>
                <!--Grid row-->

                <!--Grid row-->
                <div class="row">
                    <div class="col-md-12">
                        <div class="md-form mb-0">
                            <input type="text" id="subject" name="subject" class="form-control">
                            <label for="subject">Betreff</label>
                        </div>
                    </div>
                </div>
                <!--Grid row-->

                <!--Grid row-->
                <div class="row">

                    <!--Grid column-->
                    <div class="col-md-12">

                        <div class="md-form">
                            <textarea type="text" id="message" name="message" rows="2" class="form-control md-textarea"></textarea>
                            <label for="message">Deine Nachricht</label>
                        </div>

                    </div>
                </div>
                <!--Grid row-->

            </form>

            <div class="text-center">
                <a class="btn btn-primary" onclick="document.getElementById('contact-form').submit();">Senden</a>
            </div>
            <c:if test="${!sessionBean.loggedIn}">
                <div class="center">
                    <hr>
                </div>
                <div class="text-center">
                    <a href="./">Zur&uuml;ck zu Login</a>
                </div>
            </c:if>

            <div class="status"></div>
        </div>
        <!--Grid column-->
    </div>

</section>
<!--Section: Contact v.2-->
</div>