<div class="text-center">
    <img src="/assets/Images/lama.png" style="width: 130px">
    <form method="POST" action="loginLogic" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">Bitte melde dich an</h1>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="text" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Passwort" required>
        <input name="isRegister" type="checkbox" hidden="hidden" id="isRegister">
        <div class="checkbox mb-3">
            <label>
                <input name="keepSignedIn" type="checkbox" value="true"> Eingeloggt bleiben
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
    </form>
    <form class="form-signin" method="GET" action="register">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Registrieren</button>
    </form>
    <form class="form-signin" method="GET" action="reset">
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
            Passwort vergessen?
        </button>
    </form>
    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
</div>