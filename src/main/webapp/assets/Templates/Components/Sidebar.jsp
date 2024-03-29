<!-- Sidebar -->
<nav id="sidebar">
    <div class="sidebar-header">
        <button type="button" id="sidebarCollapse" class="btn">
            <h3>WG - Planer</h3>
            <strong>WG</strong>
        </button>
    </div>
    <ul class="list-unstyled components">
        <li>
            <a href="/home">
                <i class="fas fa-home"></i>
                Home
            </a>
            <a href="/financial">
                <i class="fas fa-money-check-alt"></i>
                Finanzielles
            </a>
            <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                <i class="fas fa-list-ul"></i>
                To-Do
            </a>
            <ul class="collapse list-unstyled" id="pageSubmenu">
                <li>
                    <a href="/todo">Alle ToDos</a>
                </li>
                <li>
                    <a href="/mytodo">Meine ToDos</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="/clean">
                <i class="fas fa-shower"></i>
                Putzplan
            </a>
        </li>
        <li>
            <a href="/shopping">
                <i class="fas fa-shopping-cart"></i>
                Einkaufsliste
            </a>
        </li>
        <li>
            <a href="/faq">
                <i class="fas fa-question"></i>
                FAQ
            </a>
        </li>
        <li>
            <a href="/contact">
                <i class="fas fa-paper-plane"></i>
                Kontakt
            </a>
        </li>
        <li>
            <a href="/team">
                <i class="fas fa-users"></i>
                Team
            </a>
        </li>
        <li>
            <a href="/impressum">
                <i class="fas fa-newspaper"></i>
                Impressum
            </a>
        </li>
        <li>
            <a href="/settings">
                <i class="fas fa-cog"></i>
                Einstellungen
            </a>
        </li>
    </ul>
    <ul class="list-unstyled components">
        <li>
            <form action="logoutLogic" method="GET">
                <button id="logout" class="btn btn-lg btn-primary btn-block" type="submit"><i class="fas fa-sign-out-alt"></i>
                    <bold>Ausloggen</bold>
                </button>
            </form>
        </li>
    </ul>
</nav>
