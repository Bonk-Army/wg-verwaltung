//Send  bought POST if you click "ok" in modal
function boughtArticle(id) {
    $( "#boughtArticle" ).click(function() {
        document.querySelector('#bought'+id).click();
    });
}

//Send remove POST if you click "ok" in modal
function removeArticle(id) {
    $( "#removeArticle" ).click(function() {
        document.querySelector('#remove'+id).click();
    });
}

$('[data-toggle="popover"]').popover()

function validDate(){
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("dateDue")[0].setAttribute('min', today);
}