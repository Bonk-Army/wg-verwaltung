<div class="text-center">
    <form method="POST" action="resetComplete" class="form-signin">
        <p style="font-size:100px">&#x1F999;</p>
        <label for="inputPW" class="sr-only">Passwort</label>
        <input type="text" id="inputPW" class="form-control" placeholder="Neues Passwort" autofocus="autofocus" name="password">
        <label for="inputPW2" class="sr-only">Passwort wiederholen</label>
        <input type="text" id="inputPW2" class="form-control" placeholder="Neues Passwort wiederholen">
        <input type="text" id="key" name="key" hidden="hidden" value="REPLACE_KEY">
        <input type="text" id="username" name="username" hidden="hidden" value="REPLACE_USERNAME">
        <button type="submit" id="submitButton" class="submit">Passwort ändern</button>
    </form>
</div>