//Send remove POST if you click "ok" in modal
function removeClean(id) {
    $("#removeClean").click(function () {
        $('#remove' + id).click();
    });
}

function changeMe(id) {
    $('#' + id).addClass('updated');
}