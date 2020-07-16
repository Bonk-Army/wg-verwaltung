<!-- Modal -->
<div class="modal fade" id="createFinancial" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Neue Ausgabe erstellen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Aufgabe eingeben
            </div>
            <form action="addFinancialEntryLogic" method="POST">
                <label for="date" class="sr-only">Deadline</label>
                <input id="date" class="form-control" placeholder="Datum" name="date" type="date" required>
                <label for="reason" class="sr-only">Grund</label>
                <input id="reason" class="form-control" placeholder="Grund" name="reason" type="text" required>
                <select id="plusOrMinus" class="form-control" name="sign">
                    <option value="+">+</option>
                    <option value="-">-</option>
                </select>
                <label for="expense" class="sr-only">Betrag</label>
                <input id="expense" class="form-control" placeholder="Betrag" name="value" type="text" pattern="[0-9]+([,.][0-9][0-9]?)?"
                       title="Deine Eingabe muss eine Zahl sein, wahlweise mit , und einer oder zwei folgemden Zahlen" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">Einnahme / Ausgabe hinzuf&uuml;gen</button>
                </div>
            </form>
        </div>
    </div>
</div>
