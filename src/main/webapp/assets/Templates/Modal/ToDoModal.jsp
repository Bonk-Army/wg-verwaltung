<!-- Modal -->
<div class="modal fade" id="todoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bist du sicher das die Aufgabe erledigt ist ?</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form action="setDoneLogic" method="POST">
                <label for="todo" class="sr-only">Aufgabe</label>
                <input id="todo" class="form-control" placeholder="here" name="todo" type="text" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">ToDo erledigt</button>
                </div>
            </form>
        </div>
    </div>
</div>