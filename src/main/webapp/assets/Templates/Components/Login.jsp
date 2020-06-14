<div class="text-center">
    <form method="POST" action="loginLogic" class="form-signin">
        <p style="font-size:100px">&#x1F999;</p>
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="username" class="sr-only">Username</label>
        <input name="username" type="user" id="username" class="form-control" placeholder="Username" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input name="password" type="password" id="password" class="form-control" placeholder="Password" required>
        <input name="isRegister" type="checkbox" hidden="hidden" id="isRegister">
        <div class="checkbox mb-3">
            <label>
                <input name="keepSignedIn" type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
    </form>
    <form class="form-signin" type="GET" action="register">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
    <form class="form-signin" type="GET" action="reset">
        <button class="btn btn-lg btn-primary btn-block" type="button" data-toggle="modal" data-target="#exampleModal">
            Forgot password?
        </button>
    </form>
</div>