//Send POST if you click "ok" in modal
function removeClean(id) {
    $( "#removeClean" ).click(function() {
        $('#remove'+id).click();
    });
}