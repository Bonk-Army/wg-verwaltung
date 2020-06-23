//Send remove POST if you click "ok" in modal
function removeExpense(id) {
    $( "#removeExpense" ).click(function() {
        $('#remove'+id).click();
    });
}