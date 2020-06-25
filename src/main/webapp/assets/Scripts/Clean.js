//Send remove POST if you click "ok" in modal
function removeClean(id) {
    $("#removeClean").click(function () {
        window.location.href = document.querySelector('#remove' + id).href;
    });
}

function changeMe(id) {
    $('#' + id).addClass('updated');
}