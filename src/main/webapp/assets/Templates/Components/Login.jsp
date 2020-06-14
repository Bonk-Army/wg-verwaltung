<div class="text-center">
    <form method="POST" action="loginLogic" class="form-signin">
        <p style="font-size:100px">&#x1F999;</p>
        <h1 class="h3 mb-3 font-weight-normal">Bitte melde dich an</h1>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Passwort" required>
        <input name="isRegister" type="checkbox" hidden="hidden" id="isRegister">
        <div class="checkbox mb-3">
            <label>
                <input name="keepSignedIn" type="checkbox" value="remember-me"> Eingeloggt bleiben
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Anmelden</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
    </form>
    <form class="form-signin" type="GET" action="register">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Registrieren</button>
    </form>
    <form class="form-signin" type="GET" action="reset">
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
            Passwort vergessen?
        </button>
    </form>
</div>