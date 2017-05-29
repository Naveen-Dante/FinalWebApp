
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
<fmt:message key="locale.cancel" var="cancel" bundle="${bundle }" />


<jsp:include page="./sidebar.jsp"></jsp:include>
<nav class="navbar navbar-default"
	style="font-color: black; width: 100%" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="font-size: 20px; cursor: pointer"
				onclick="openNav()">&#9776;${library }</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/command?name=books">${books }</a></li>
				<c:if test="${user!= null}">
					<li><a href="/command?name=favourites">${favs }</a></li>
				</c:if>
			</ul>
			<form class="navbar-form navbar-left" role="search" action="command">
				<div class="input-group">
					<input type="hidden" name="name" value="search"> <input
						type="text" class="form-control" name="searchText"
						placeholder="${search }" required>
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
			<input type="hidden" id="language" value=${language }>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${user == null }">
					<li><a class="clickable" id="signupBtn"><b>${signup }</b></a></li>
					<li><a class="clickable" id="loginBtn"><b>${login }</b> </a></li>
				</c:if>
				<c:if test="${user!= null }">
					<li></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">${user.firstName} <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a class="clickable" id="profileBtn">Profile</a></li>
							<li><a class="clickable" id="passBtn">Change Password</a></li>
						</ul></li>
					<li><a href="/command?name=logout">${logout }</a></li>
				</c:if>
				<li><a class="clickable" id="engBtn">English</a></li>
				<li><a class="clickable" id="espBtn">Espanol</a></li>
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
						<label class="control-label col-sm-4" for="First Name">${first }:
						</label>
						<div class="col-sm-8" data-toggle="tooltip"
							title="Enter your First Name.">
							<input type="text" class="form-control" name="first-name"
								placeholder="Enter Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid First Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Last Name">${last }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="last-name"
								placeholder="Enter Surname" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Last Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="User Name">${username }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="user-name"
								placeholder="Enter User Name" required pattern="[a-zA-Z]{4,}"
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid User Name Here(minimum 4 alphabets)')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Phone">${phone }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="phone"
								pattern="[789][0-9]{9}" placeholder="Enter Phone Number"
								required oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Phone Number.[at least 10 digits]')">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="email">${email }:
						</label>
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
<div class="modal fade" id="profile" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-header-profile"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-user"></span> ${user.firstName }
					${user.lastName }
				</h4>
			</div>
			<form class="form-horizontal" action="command?name=update"
				method="post">
				<input type="hidden" id="queryUrl" name="queryUrl"
					value="${pageContext.request.queryString }">
				<div class="modal-body" style="padding: 30px 80px;">

					<div class="form-group">
						<label class="control-label col-sm-4" for="First Name">${first }:
						</label>
						<div class="col-sm-8" data-toggle="tooltip"
							title="Enter your First Name.">
							<input type="text" class="form-control" id="fname"
								name="first-name" value="${user.firstName }" required
								pattern="[a-zA-Z]{4,}" oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid First Name Here(minimum 4 alphabets)')"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Last Name">${last }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="lname"
								name="last-name" value="${user.lastName }" required
								pattern="[a-zA-Z]{4,}" oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Last Name Here(minimum 4 alphabets)')"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="User Name">${username }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="user-name"
								value="${user.userName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Phone">${phone }:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="phone" name="phone"
								pattern="[0-9]{10}" value="${user.phoneNumber }" required
								oninput="setCustomValidity('')"
								oninvalid="this.setCustomValidity('Enter Valid Phone Number.[10 digits, no spaces]')"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="email">${email }:
						</label>
						<div class="col-sm-8">
							<input type="email" class="form-control" id="email" name="email"
								value="${user.email}" disabled>
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default"
						id="editProfileBtn">
						<span class="glyphicon glyphicon-edit">Edit</span>
					</button>
					<button type="submit"
						class="btn btn-danger btn-default hide pull-left"
						id="upProfileBtn">
						<span class="glyphicon glyphicon-lock"></span> Update
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="modal fade" id="changepassword" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-header-warning"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-pencil"></span> Change Password
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 80px;">
				<form action="command?name=changepassword" role="form" method="post">
					<input type="hidden" id="queryUrl" name="queryUrl"
					value="${pageContext.request.queryString }">
					<div class="form-group">
						<label for="usrname"><span
							class="glyphicon glyphicon-user"></span> ${password }</label> <input
							type="text" class="form-control" name="password"
							placeholder="Enter Old Password" required>
					</div>
					<div class="form-group">
						<label for="psw"><span
							class="glyphicon glyphicon-eye-open"></span> New ${password }</label> <input
							type="password" class="form-control" name="new-password"
							placeholder="Enter New password" required>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span> Change Password
					</button>
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

<input type="hidden" id="reqUrl"
	value="${pageContext.request.requestURI }">
