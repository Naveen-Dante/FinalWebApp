
$(document).on("click","#signupBtn",function(){
	closeNav();
	$('#login').modal('hide');
	$('#favs').modal('hide');
	$('#signup').modal('show');
});
$(document).on("click","#loginBtn",function(){
	closeNav();
	$('#signup').modal('hide');
	$('#favs').modal('hide');
	$('#login').modal('show');
});
$(document).on("click","#favsBtn",function(){
	closeNav();
	   if($('#isLoggedIn').length){
		   $.get('command?name=books',function(){	   
			   
		   });
	   }
	   else{
		   $('#favs').modal('show');
	   }
});

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementsByClassName("main")[0].style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementsByClassName("main")[0].style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}