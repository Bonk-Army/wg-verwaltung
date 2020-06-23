$(document).ready(function () {
    $('#visibleSavePasswordButton').on('click', function () {
        let password = document.querySelector('#password').value;
        let repeatedPassword = document.querySelector('#password2').value;
        let username = document.querySelector('#username').value;
        if(password === repeatedPassword && password !== ""){
            if(password.toLowerCase().includes(username.toLowerCase())){
                window.alert("Benutze nicht den Benutzername in deinem Passwort");
            } else{
                $('#savePasswordButton').click();
            }
        } else {
            window.alert(unescape("Deine Passw%F6rter sind nicht identisch.%0A"));
        }
    });
});

function leaveWg() {
    $( "#leaveWG" ).click(function() {
        $('#leaveWgButton').click();
    });
}