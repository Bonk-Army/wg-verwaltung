function boughtArticle(id) {
    $( "#boughtArticle" ).click(function() {
        $('#'+id).click();
    });
}

function removeArticle(id) {
    $( "#removeArticle" ).click(function() {
        $('#remove'+id).click();
    });
}