$(document).ready(function () {
    $('#visibleRegisterButton').on('click', function () {
        let password = document.querySelector('#password').value;
        let repeatedPassword = document.querySelector('#password2').value;
        if(password === repeatedPassword && password !== ""){
            $('#registerButton').click();
        } else {
            window.alert(unescape("Deine Passw%F6rter sind nicht identisch.%0A"));
        }
    });
});
