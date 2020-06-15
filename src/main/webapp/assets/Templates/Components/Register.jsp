<div class="text-center">
    <img src="/assets/Images/lama.png" style="width: 130px">
    <form class="form-signin" method="POST" action="loginLogic">
        <h1 class="h3 mb-3 font-weight-normal">Bitte registriere dich</h1>
        <label for="firstName" class="sr-only">Vorname</label>
        <input name="firstName" type="text" id="firstName" class="form-control" placeholder="Vorname" required autofocus>
        <label for="lastName" class="sr-only">Nachname</label>
        <input name="lastName" type="text" id="lastName" class="form-control" placeholder="Nachname" required autofocus>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="text" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <div id="strengthbar"><div id="bar" class="bar0"></div></div>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" onkeyup="checkPassword()" type="password" id="password" class="form-control" placeholder="Passwort" required st>
        <label for="password2" class="sr-only">Passwort wiederholen</label>
        <input type="password" id="password2" class="form-control" placeholder="Passwort wiederholen" required>
        <label for="email" class="sr-only">E-Mail</label>
        <input name="email" type="email" id="email" class="form-control" placeholder="E-Mail-Adresse" required>
        <div class="checkbox mb-3">
            <label>
                <input name="keepSignedIn" type="checkbox" value="true"> Eingeloggt bleiben
            </label>
        </div>
        <input name="isRegister" type="checkbox" checked="checked" hidden="hidden" id="isRegister">
        <button id="visibleRegisterButton" class="btn btn-lg btn-primary btn-block" type="button">Registrieren</button>
        <button id="registerButton" class="btn btn-lg btn-primary btn-block" type="submit" style="display: none;">Abschicken</button>
    </form>
    <div id="backButton"><a href="/" style="text-decoration: none">
        <button id="loginPage" class="btn btn-lg btn-primary btn-block" type="button">zur&uuml;ck zur Login-Seite
        </button>
    </a></div>
</div>