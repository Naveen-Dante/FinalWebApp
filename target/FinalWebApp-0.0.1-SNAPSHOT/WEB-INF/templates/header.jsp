<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<nav class="navbar navbar-default" style="font-color: black"
	role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="controller">My Library</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="command?name=books">Books</a></li>
				<li><a href="command?name=books">Favourites</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search" action="/command?name=search">
				<div class="form-group">
					<input type="text" class="form-control" name="searchText"
						placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${user == null }">
					<li><a class="clickable" id="signupBtn"><b>SignUp</b></a></li>
					<li><a class="clickable" id="loginBtn"><b>Login</b> </a></li>
				</c:if>
				<c:if test="${user!= null }">
					<li><a href="command?name=profile">${user.firstName}</a></li>
					<li><a href="command?name=logout">LogOut</a></li>
				</c:if>
				<li><a href="command?name=translate&lang=en_EN">English</a></li>
				<li><a href="command?name=translate&lang=es_ES">Espanol</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="modal fade" id="login" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-info"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-lock"></span> Login
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 50px;">
				<form action="command?name=login" role="form" method="post">
					<div class="form-group">
						<label for="usrname"><span
							class="glyphicon glyphicon-user"></span> Username</label> 
						<input type="text" class="form-control" name="username"
							placeholder="Enter User Name" required>
					</div>
					<div class="form-group">
						<label for="psw"><span
							class="glyphicon glyphicon-eye-open"></span> Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Enter password" required>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span> Login
					</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left"
					data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> Cancel
				</button>
				<p>
					Not a member? <a id="signupBtn">Sign Up</a>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="signup" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-header-info"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-user"></span> Sign Up
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 50px;">
				<form class="form-horizontal" action="user" method="post">
					<div class="form-group">
						<label class="control-label col-sm-4" for="First Name">First
							Name: </label>
						<div class="col-sm-8" data-toggle="tooltip"
							title="Enter your First Name.">
							<input type="text" class="form-control" name="first-name"
								placeholder="Enter Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid First Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Last Name">Last
							Name: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="last-name"
								placeholder="Enter Surname" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Last Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="User Name">User
							Name: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="user-name"
								placeholder="Enter User Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid User Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Phone">Phone
							Number: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="phone"
								pattern="[789][0-9]{9}" placeholder="Enter Phone Number"
								required oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Phone Number.[at least 10 digits]')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="email">Email: </label>
						<div class="col-sm-8">
							<input type="email" class="form-control" name="email"
								placeholder="Enter Email" required
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Email Here')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="password">Password:
						</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="password"
								placeholder="Enter password" required>
						</div>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span>Signup
					</button>
					<!--<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" name="button" value="new"
								class="btn btn-default">
								<fmt:message key="signup" bundle="${bundle }"></fmt:message>
							</button>
						</div>
					</div>-->
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left"
					data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> Cancel
				</button>
			</div>
		</div>
	</div>
</div>
