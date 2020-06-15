<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Neue WG erstellen</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                WG-Namen eingeben
            </div>
            <form action="createWGLogic" method="POST">
                <label for="wgname" class="sr-only">WGName</label>
                <input id="wgname" class="form-control" placeholder="WG-Name" name="wgname" required>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary odom-submit">WG erstellen</button>
                </div>
            </form>
        </div>
    </div>
</div>