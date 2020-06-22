<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <jsp:useBean id="sessionBean" class="beans.SessionBean" scope="session"/>
    <jsp:useBean id="settingBean" class="beans.SettingsBean" scope="request"/>
    <jsp:setProperty name="settingBean" property="userId" value="${sessionBean.userId}"/>
    <h2>Settings</h2>
    <form action="changeNameLogic" method="POST">
        <label for="username">Benutzername</label>
        <input id="username" class="form-control" type="text" placeholder="${sessionBean.username}" readonly="readonly"/>
        <label for="firstName">Vorname</label>
        <input id="firstName" name="firstName" class="form-control" type="text" value="${sessionBean.firstName}"/>
        <label for="lastName">Nachname</label>
        <input id="lastName" name="lastName" class="form-control" type="text" value="${sessionBean.lastName}"/>
        <label for="email">E-Mail</label>
        <input class="form-control" type="email" id="email" placeholder="${sessionBean.email}" readonly="readonly" required>
        <button id="saveButton" class="btn btn-lg btn-primary btn-block" type="submit">&Auml;nderungen speichern</button>
    </form>
    <c:choose>
        <c:when test="${settingBean.userHasWg}">
            <label for="wg">WG</label>
            <input id="wg" class="form-control" placeholder="${sessionBean.wgName}" readonly="readonly"/>
            <form action="leaveWgLogic" method="GET">
                <button id="leaveWgButtonModal" onclick="leaveWg()" class="btn btn-lg btn-primary btn-block leave" type="button"
                        data-toggle="modal" data-target="#leaveWgModal">Aktuelle WG verlassen
                </button>
                <button id="leaveWgButton" class="btn btn-lg btn-primary btn-block leave" type="submit" style="display: none">Abschicken</button>
            </form>
        </c:when>
        <c:otherwise>
            <a href="/wgverwaltung">
                <button id="joinWg" class="btn btn-lg btn-primary btn-block join" type="submit">WG beitreten oder neue WG erstellen</button>
            </a>
        </c:otherwise>
    </c:choose>
    <form action="changePasswordLogic" method="POST">
        <label for="oldPassword">Altes Passwort</label>
        <input id="oldPassword" class="form-control" name="oldPassword" type="password"/>
        <label for="password">Neues Passwort</label>
        <div id="strengthbar" style="max-width: 500px;">
            <div id="bar" class="bar0"></div>
        </div>
        <input id="password" onkeyup="checkPassword()" name="newPassword" class="form-control" type="password"/>
        <label for="password2">Neues Passwort wiederholen</label>
        <input id="password2" class="form-control" type="password"/>
        <button id="visibleSavePasswordButton" class="btn btn-lg btn-primary btn-block" type="button">Passwort &auml;ndern</button>
        <button id="savePasswordButton" class="btn btn-lg btn-primary btn-block" type="submit" style="display: none;">Abschicken</button>
    </form>
</div>