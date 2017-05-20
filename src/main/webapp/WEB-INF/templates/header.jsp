
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:message key="locale.library" var="library" bundle="${bundle }" />
<fmt:message key="locale.books" var="books" bundle="${bundle }" />
<fmt:message key="locale.search" var="search" bundle="${bundle }" />
<fmt:message key="locale.signup" var="signup" bundle="${bundle }" />
<fmt:message key="locale.signin" var="signin" bundle="${bundle }" />
<fmt:message key="locale.login" var="login" bundle="${bundle }" />
<fmt:message key="locale.logout" var="logout" bundle="${bundle }" />
<fmt:message key="locale.user" var="username" bundle="${bundle }" />
<fmt:message key="locale.pass" var="password" bundle="${bundle }" />
<fmt:message key="locale.email" var="email" bundle="${bundle }" />
<fmt:message key="locale.first" var="first" bundle="${bundle }" />
<fmt:message key="locale.last" var="last" bundle="${bundle }" />
<fmt:message key="locale.phone" var="phone" bundle="${bundle }" />
<fmt:message key="locale.favs" var="favs" bundle="${bundle }" />


<jsp:include page="./sidebar.jsp"></jsp:include>
<nav class="navbar navbar-default" style="font-color: black; width: 100%"
	role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776;${library }</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="command?name=books">${books }</a></li>
				<li><a class="clickable" id="favsBtn">${favs }</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search"
				action="/command?name=search">
				<div class="input-group">
					<input type="text" class="form-control" name="searchText"
						placeholder="${search }" required>
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${user == null }">
					<li><a class="clickable" id="signupBtn"><b>${signup }</b></a></li>
					<li><a class="clickable" id="loginBtn"><b>${login }</b> </a></li>
				</c:if>
				<c:if test="${user!= null }">
					<li><a href="command?name=profile" id="isLoggedIn">${user.firstName}</a></li>
					<li><a href="command?name=logout">${logout }</a></li>
				</c:if>
				<li><a href="command?name=translate&lang=en_EN">English</a></li>
				<li><a href="command?name=translate&lang=es_ES">Espanol</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="modal fade" id="favs" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-warning"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-warning-sign"></span> Information
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 80px;">
				<div>
					<h4>Login to Proceed!!!!</h4>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left"
					data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> ${cancel }
				</button>
				<button type="submit" class="btn btn-danger btn-default pull-right"
					data-dismiss="modal" id="loginBtn">
					<span class="glyphicon glyphicon-user"></span> ${login }
				</button>
				<p>
					${locale.signup_msg } <a id="signupBtn">${signup }</a>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="login" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-info"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-lock"></span> ${login }
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 50px;">
				<form action="command?name=login" role="form" method="post">
					<div class="form-group">
						<label for="usrname"><span
							class="glyphicon glyphicon-user"></span> ${username }</label> <input
							type="text" class="form-control" name="username"
							placeholder="Enter User Name" required>
					</div>
					<div class="form-group">
						<label for="psw"><span
							class="glyphicon glyphicon-eye-open"></span> ${password }</label> <input
							type="password" class="form-control" name="password"
							placeholder="Enter password" required>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span> ${login }
					</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left"
					data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> ${cancel }
				</button>
				<p>
					${locale.signup_msg } <a id="signupBtn">${signup }</a>
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
					<span class="glyphicon glyphicon-user"></span> ${signup }
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 50px;">
				<form class="form-horizontal" action="command?name=new"
					method="post">
					<div class="form-group">
						<label class="control-label col-sm-4" for="First Name">${first }: </label>
						<div class="col-sm-8" data-toggle="tooltip"
							title="Enter your First Name.">
							<input type="text" class="form-control" name="first-name"
								placeholder="Enter Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid First Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Last Name">${last }: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="last-name"
								placeholder="Enter Surname" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Last Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="User Name">${username }: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="user-name"
								placeholder="Enter User Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid User Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Phone">${phone }: </label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="phone"
								pattern="[789][0-9]{9}" placeholder="Enter Phone Number"
								required oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Phone Number.[at least 10 digits]')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="email">${email }: </label>
						<div class="col-sm-8">
							<input type="email" class="form-control" name="email"
								placeholder="Enter Email" required
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Email Here')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="password">${password }:
						</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="password"
								placeholder="Enter password" required>
						</div>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span>${signup }
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
					<span class="glyphicon glyphicon-remove"></span> ${cancel }
				</button>
			</div>
		</div>
	</div>
</div>
