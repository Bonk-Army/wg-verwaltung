$(document).ready(function () {
    $('#visibleSaveButton').on('click', function () {
        let password = document.querySelector('#password').value;
        let repeatedPassword = document.querySelector('#password2').value;
        if(password === repeatedPassword){
            $('#saveButton').click();
        } else {
            window.alert(unescape("Deine Passw%F6rter sind nicht identisch.%0A"));
        }
    });
});