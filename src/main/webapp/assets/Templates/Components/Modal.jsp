<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Register</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                E-Mail-Adresse eingeben!
            </div>
            <form action="reset" method="POST">
                <label for="eMail" class="sr-only">E-Mail</label>
                <input type="email" id="eMail" class="form-control" placeholder="E-Mail-Adresse" name="email" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">Passwort zur&uuml;cksetzen</button>
                </div>
            </form>
        </div>
    </div>
</div>