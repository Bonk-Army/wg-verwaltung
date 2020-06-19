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
        <label for="username">Benutzername</label><input id="username" name="username" type="text" readonly="readonly"/>
        <label for="firstname">Vorname</label><input id="firstname" name="firstname" type="text"/>
        <label for="lastname">Nachname</label><input id="lastname" name="lastname" type="text"/>
        <label for="email" class="sr-only">E-Mail</label>
        <input name="email" type="email" id="email" class="form-control" placeholder="E-Mail-Adresse" readonly="readonly" required>
        <label for="wg">WG</label><input id="wg"/>
        <div id="strengthbar"><div id="bar" class="bar0"></div></div>
        <label for="password">Passwort</label><input id="password" name="password" type="password"/>
        <label for="password2">Passwort wiederholen</label><input id="password2" type="password"/>
        <button id="save" type="submit">&Auml;nderungen speichern</button>
    </form>
</div>
