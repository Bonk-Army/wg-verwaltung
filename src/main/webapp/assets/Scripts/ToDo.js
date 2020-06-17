function doneTodo(id) {
    $( "#doneToDo" ).click(function() {
        $('#'+id).click();
    });
}

function removeTodo(id) {
    $( "#removeToDo" ).click(function() {
        $('#remove'+id).click();
    });
}