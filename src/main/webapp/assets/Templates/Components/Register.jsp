<div class="text-center">
    <img src="/assets/Images/lama.png" style="width: 130px">
    <form class="form-signin" method="POST" action="loginLogic">
        <h1 class="h3 mb-3 font-weight-normal">Bitte registriere dich</h1>
        <label for="firstName" class="sr-only">Vorname</label>
        <input name="firstName" type="user" id="firstName" class="form-control" placeholder="Vorname" required autofocus>
        <label for="lastName" class="sr-only">Nachname</label>
        <input name="lastName" type="user" id="lastName" class="form-control" placeholder="Nachname" required autofocus>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Passwort" required>
        <label for="password2" class="sr-only">Passwort wiederholen</label>
        <input name="password2" type="password" id="password2" class="form-control" placeholder="Passwort wiederholen" required>
        <label for="email" class="sr-only">E-Mail</label>
        <input name="email" type="email" id="email" class="form-control" placeholder="E-Mail-Adresse" required>
        <input name="isRegister" type="checkbox" checked="checked" hidden="hidden" id="isRegister">
        <button id="visibleRegisterButton" class="btn btn-lg btn-primary btn-block" type="button">Registrieren</button>
        <button id="registerButton" class="btn btn-lg btn-primary btn-block" type="submit" style="display: none;">Abschicken</button>
    </form>
</div>