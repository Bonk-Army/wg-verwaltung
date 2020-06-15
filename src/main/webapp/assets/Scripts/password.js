function checkPassword() {
    let strength = 0;
    let pw = document.getElementById('password').value;
    // Prüfung min. ein Großbuchstabe
    if (pw.match(/[A-Z]+/)){
        strength++;
    }
    // Prüfung min. ein Kleinbuchstabe
    if (pw.match(/[a-z]+/)){
        strength++;
    }
    // Prüfung min. eine Zahl
    if (pw.match(/[0-9]+/)){
        strength++;
    }
    // Prüfung min. ein Sondezeichen
    if (pw.match(/[!"§$%&/()=?@#'+*~^°\-_,;.:<>´`{}\[\]]+/)){
        strength++;
    }
    // Prüfung min. Länge 8
    if (pw.length >=8){
        strength++;
    }
    document.querySelector('#bar').className = 'bar' + strength;
}