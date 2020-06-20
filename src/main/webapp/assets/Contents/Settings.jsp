<%--
  Created by IntelliJ IDEA.
  User: kristin.agne
  Date: 19.06.2020
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<div id="content">
    <h2>Settings</h2>
    <form action="settingLogic" method="POST">
        <label for="username">Benutzername</label>
        <input id="username" class="form-control" type="text" readonly="readonly"/>
        <label for="firstname">Vorname</label>
        <input id="firstname" name="firstname" class="form-control" type="text"/>
        <label for="lastname">Nachname</label>
        <input id="lastname" name="lastname" class="form-control" type="text"/>
        <label for="email">E-Mail</label>
        <input class="form-control" type="email" id="email" class="form-control" readonly="readonly" required>
        <label for="wg">WG</label>
        <input id="wg" class="form-control" readonly="readonly"/>
        <label for="password">Passwort</label>
        <div id="strengthbar"><div id="bar" class="bar0"></div></div>
        <input id="password" onkeyup="checkPassword()" name="password" class="form-control" type="password"/>
        <label for="password2">Passwort wiederholen</label>
        <input id="password2" class="form-control" type="password"/>
        <button id="visibleSaveButton" class="btn btn-lg btn-primary btn-block" type="button">&Auml;nderungen speichern</button>
        <button id="saveButton" class="btn btn-lg btn-primary btn-block" type="submit" style="display: none;">Abschicken</button>
    </form>
</div>