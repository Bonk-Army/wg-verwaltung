//Send done POST if you click "ok" in modal
function doneTodo(id) {
    $( "#doneToDo" ).click(function() {
        $('#'+id).click();
    });
}

//Send remove POST if you click "ok" in modal
function removeTodo(id) {
    $( "#removeToDo" ).click(function() {
        $('#remove'+id).click();
    });
}