//Changes layout of sidebar by clicking on "Wg-Organisator"-button adding a class
$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
});