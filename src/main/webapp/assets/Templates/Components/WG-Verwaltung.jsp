<div class="text-center">
    <img src="/assets/Images/lama.png" style="width: 130px">
    <form method="POST" action="loginLogic" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">WG Beitreten</h1>
        <label for="username" class="sr-only">WG-Code</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="WG-Code" required autofocus>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Beitreten</button>
    </form>
    <form class="form-signin" type="GET" action="reset">
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
            Neue WG erstellen ?
        </button>
    </form>
    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
</div>