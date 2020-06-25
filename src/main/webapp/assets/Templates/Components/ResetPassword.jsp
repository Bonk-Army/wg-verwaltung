<div id=content" class="text-center">
    <form method="POST" action="resetComplete" class="form-signin">
        <img src="/assets/Images/lama.png" style="height: 220px">
        <div id="strengthbar"><div id="bar" class="bar0"></div></div>
        <label for="password" onkeyup="checkPassword()" class="sr-only">Passwort</label>
        <input type="password" id="password" class="form-control" placeholder="Neues Passwort" autofocus="autofocus" name="password">
        <label for="password2" class="sr-only">Passwort wiederholen</label>
        <input type="password" id="password2" class="form-control" placeholder="Neues Passwort wiederholen">
        <input type="text" id="key" name="key" hidden="hidden" value="<%= request.getParameter("key") %>">
        <input type="text" id="username" name="username" hidden="hidden" value="<%= request.getParameter("uname") %>">
        <button id="visibleResetPasswordButton" class="btn btn-lg btn-primary btn-block" type="button">Passwort &auml;ndern</button>
        <button type="submit" id="resetPasswordButton" style="display: none;"></button>
    </form>
</div>