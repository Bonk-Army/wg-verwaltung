//Send done POST if you click "ok" in modal
function doneTodo(id) {
    $( "#doneToDo" ).click(function() {
        document.querySelector('#done'+id).click();
    });
}

//Send remove POST if you click "ok" in modal
function removeTodo(id) {
    $( "#removeToDo" ).click(function() {
        document.querySelector('#remove'+id).click();
    });
}

$('[data-toggle="popover"]').popover()