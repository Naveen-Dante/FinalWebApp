<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}"
	scope="session" />
<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="resource_bundle/locale" var="bundle"
	scope="session" />
<fmt:message key="locale.welcome" var="welcome" bundle="${bundle }" />

<!DOCTYPE html >
<html lang=${language }>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${sessionScope.get('user') == null || sessionScope.get('user').isAdmin() == false}">
    <c:redirect url="/"/>
</c:if>
<script src="https://use.fontawesome.com/5794b2dac0.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="./css/admin.css" rel="stylesheet">
<script src="./js/admin.js"></script>
<link href="./css/tiles.css" rel="stylesheet">
<title>Update Book</title>
</head>
<body lang="${language }"
	style="background-color: rgba(0, 0, 0, 0.85); color: white">
	<jsp:include page="../templates/adminNavbar.jsp"></jsp:include>
	<div class="main">
		<div class="container" style="max-width: 800px">
			<h2>Update Book</h2>
			<form method="post" action="/admin?command=update">
				<div class="form-group">
					<label for="title">Title:</label> <input type="text"
						class="form-control" name="title" id="title"
						value="${book.title }" disabled>
				</div>
				<div class="form-group">
					<label for="author">Author:</label> <input type="text"
						class="form-control" name="author" id="author"
						value="${book.author }" disabled>
				</div>
				<div class="form-group">
					<label for="imageurl">ImageUrl:</label> <input type="text"
						class="form-control" name="imageurl" id="imageurl"
						value="${book.imageUrl }" disabled>
				</div>
				<div class="form-group">
					<label for="description">Description:</label>
					<textarea name="description" rows="10" class="form-control" name="description"
						id="description" disabled>${book.description }</textarea>
				</div>
				<input type="hidden" name="id" value="${book.id }"> <input
					type="hidden" name="language" value="${book.language }">
				<div class="row">
					<button type="submit" class="btn btn-success hide pull-left" name="updateBtn">Submit</button>
					<button type="button" class="btn btn-info pull-left" name="editBookBtn"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
					<a href="/admin?command=remove-book&id=${book.id }&language=${book.language }"><button
							type="button" class="btn btn-danger pull-right">Delete
							Book</button></a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>