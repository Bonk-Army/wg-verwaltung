<div id="content" class="text-center">
    <img src="/assets/Images/security-llama.png" style="height: 220px">
    <form method="POST" action="loginLogic" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">Bitte melde dich an</h1>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Passwort" required>
        <input name="isRegister" type="checkbox" hidden="hidden" id="isRegister">
        <div class="checkbox mb-3">
            <label>
                <input name="keepSignedIn" type="checkbox" value="true" checked="checked"> Eingeloggt bleiben
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
    </form>
    <form class="form-signin" type="GET" action="register">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Registrieren</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
            Passwort vergessen?
        </button>
    </form>

    <div class="center">
        <hr>
    </div>
    <div class="text-center">
        <a href="./contact">Kontakt</a>  &emsp; <a href="./faq">FAQ's </a>  &emsp; <a href="./impressum">Impressum </a>
    </div>
    <div class="center">
        <hr>
    </div>
    <div class="text-center">
        Team Lama &copy; 2020
    </div>
</div>