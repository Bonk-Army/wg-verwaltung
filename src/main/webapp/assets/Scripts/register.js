$(document).ready(function () {
    $('#visibleRegisterButton').on('click', function () {
        password = document.querySelector('#password').value;
        repeatedPassword = document.querySelector('#password2').value;
        if(password === repeatedPassword){
            $('#registerButton').click();
        } else {
            window.alert(unescape("Deine Passw%F6rter sind nicht identisch.%0A"));
        }
    });
});
