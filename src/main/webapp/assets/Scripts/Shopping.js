//Send  bought POST if you click "ok" in modal
function boughtArticle(id) {
    $( "#boughtArticle" ).click(function() {
        $('#'+id).click();
    });
}

//Send remove POST if you click "ok" in modal
function removeArticle(id) {
    $( "#removeArticle" ).click(function() {
        document.querySelector('#remove'+id).click();
    });
}