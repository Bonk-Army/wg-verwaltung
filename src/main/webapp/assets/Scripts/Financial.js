//Send remove POST if you click "ok" in modal
function removeExpense(id) {
    $( "#removeExpense" ).click(function() {
        document.querySelector('#remove'+id).click();
    });
}