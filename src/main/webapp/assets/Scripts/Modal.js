$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})
$('#myFormSubmit').click(function(e){
    e.preventDefault();
    alert($('#myField').val());
});