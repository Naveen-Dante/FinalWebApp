
$(document).on("click","#engBtn",function(){
	if($('html').attr('lang') != 'en_US'){
		$.get('command?name=changelanguage&lang=en_US',function(){
			if($('#queryUrl').val().length < 1){
				$.get('command?name=books',function(){
					window.location.reload();
				});
			}
			else{
				$.get('command?'+$('#queryUrl').val(),function(){
					window.location.reload();
				});
			}
		});
	}
});

$(document).on("click","#espBtn",function(){
	if($('html').attr('lang') != 'es_ES'){
		$.get('command?name=changelanguage&lang=es_ES',function(){
			if($('#queryUrl').val().length < 1){
				$.get('command?name=books',function(){
					window.location.reload();
				});
			}
			else{
				$.get('command?'+$('#queryUrl').val(),function(){
					window.location.reload();
				});
			}
		});
	}
});

$(document).on("click","#signupBtn",function(){
	$('#login').modal('hide');
	$('#favs').modal('hide');
	$('#signup').modal('show');
});
$(document).on("click","#loginBtn",function(){
	$('#signup').modal('hide');
	$('#favs').modal('hide');
	$('#login').modal('show');
});
$(document).on("click","#profileBtn", function(){
	$('#profile').modal('show');
});
$(document).on("click","#editProfileBtn", function () {
	$('#fname').prop('disabled',false);
	$('#lname').prop('disabled',false);
	$('#phone').prop('disabled',false);
	$('#upProfileBtn').removeClass('hide');
	$('#editProfileBtn').addClass('hide');
});
$(document).on('click','#passBtn', function(){
	$('#changepassword').modal('show');
});