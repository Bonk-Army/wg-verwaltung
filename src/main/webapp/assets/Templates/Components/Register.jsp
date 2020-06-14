<div class="text-center">
    <!--img src="/assets/Images/lama.png" style="width: 130px"-->
    <form class="form-signin" method="POST" action="loginLogic">
        <p style="font-size:100px">&#x1F999;</p>
        <h1 class="h3 mb-3 font-weight-normal">Bitte registriere dich</h1>
        <label for="username" class="sr-only">Benutzername</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="Benutzername" required autofocus>
        <label for="password" class="sr-only">Passwort</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Passwort" required>
        <label for="password2" class="sr-only">Passwort wiederholen</label>
        <input name="password2" type="password" id="password2" class="form-control" placeholder="Passwort wiederholen" required>
        <label for="email" class="sr-only">E-Mail</label>
        <input name="email" type="email" id="email" class="form-control" placeholder="E-Mail-Adresse" required>
        <input name="isRegister" type="checkbox" checked="checked" hidden="hidden" id="isRegister">
        <button id="registerButton" class="btn btn-lg btn-primary btn-block inactive" type="submit">Registrieren</button>
    </form>
</div>