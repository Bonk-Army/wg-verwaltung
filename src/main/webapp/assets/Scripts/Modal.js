$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})
$('#myFormSubmit').click(function(e){
    e.preventDefault();
    alert($('#myField').val());
    /*
    $.post('http://path/to/post',
       $('#myForm').serialize(),
       function(data, status, xhr){
         // do something here with response;
       });
    */
});