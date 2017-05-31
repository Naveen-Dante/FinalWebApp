
$(document).on("click","#newBookBtn",function(){
	$('#newBook').modal('show');
});

$(document).on("click","#editBookBtn", function () {
	$('#title').prop('disabled',false);
	$('#author').prop('disabled',false);
	$('#imageurl').prop('disabled',false);
	$('#description').prop('disabled',false);
	$('#updateBtn').removeClass('hide');
	$('#editBookBtn').addClass('hide');
});