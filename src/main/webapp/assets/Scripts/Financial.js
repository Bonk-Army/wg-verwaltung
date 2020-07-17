//Send remove POST if you click "ok" in modal
function removeExpense(id) {
    $("#removeExpense").click(function () {
        document.querySelector('#remove' + id).click();
    });
}

function validDate() {
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("date")[0].setAttribute('max', today);
}