<div class="text-center">
    <img src="/assets/Images/lama.png" style="width: 130px">
    <form method="GET" action="joinWGLogic" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">WG Beitreten</h1>
        <label for="wgcode" class="sr-only">WG-Code</label>
        <input name="wgcode" type="text" id="wgcode" class="form-control" placeholder="WG-Code" required autofocus>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Beitreten</button>
    </form>
    <form class="form-signin" method="POST" action="createWGLogic">
        <label for="wgname" class="sr-only">WG-Name</label>
        <input id="wgname" name="wgname" type="text" class="form-control" placeholder="WG-Name" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit" data-toggle="modal" data-target="#exampleModal">
            Neue WG Erstellen
        </button>
    </form>
    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
</div>