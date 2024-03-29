<!-- Modal -->
<div class="modal fade" id="createToDo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Neue Aufgabe erstellen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Aufgabe eingeben
            </div>
            <form action="addTodoLogic" method="POST">
                <label for="article" class="sr-only">Aufgabe</label>
                <input id="article" class="form-control" placeholder="Neue Aufgabe erstellen" name="todo" type="text" required>
                <h6>Wem m&ouml;chtest du diese Aufgabe zuteilen?</h6>
                <label for="name" class="sr-only">Name</label>
                <select id="name" class="form-control" name="username" required>
                    <c:forEach items="${todoBean.usersOfWg}" var="user">
                        <option value="${user.username}">${user.nameString}</option>
                    </c:forEach>
                </select>
                <h6>Bis wann soll die Aufgabe erledigt sein?</h6>
                <input id="date" class="form-control date" name="date" type="date" required>
                <label for="time" class="sr-only">Deadline</label>
                <input id="time" class="form-control time" name="time" type="time" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">ToDo erstellen</button>
                </div>
            </form>
        </div>
    </div>
</div>