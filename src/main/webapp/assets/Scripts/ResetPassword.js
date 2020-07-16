$(document).ready(function () {
    $('#visibleResetPasswordButton').on('click', function () {
        let password = document.querySelector('#password').value;
        let repeatedPassword = document.querySelector('#password2').value;
        let username = document.querySelector('#username').value;
        //Check if both passwords are similiar
        if (password === repeatedPassword && password !== "") {
            //Check if username is part of password
            if (password.toLowerCase().includes(username.toLowerCase())) {
                window.alert("Benutze nicht den Benutzername in deinem Passwort");
            } else {
                $('#resetPasswordButton').click();
            }
        } else {
            window.alert(unescape("Deine Passw%F6rter sind nicht identisch."));
        }
    });
});
