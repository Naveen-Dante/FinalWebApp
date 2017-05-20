<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.epam.domain.User, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${language }" scope="session" />
<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="resource_bundle/locale" var="bundle"
	scope="session" />
<fmt:message key="locale.lib" bundle="${bundle }" var="title"></fmt:message>
<fmt:message key="locale.book_id" bundle="${bundle }" var="book_id"></fmt:message>
<fmt:message key="locale.title" bundle="${bundle }" var="book_title"></fmt:message>
<fmt:message key="locale.author" bundle="${bundle }" var="author"></fmt:message>
<fmt:message key="locale.information" bundle="${bundle }" var="info"></fmt:message>
<fmt:message key="locale.type" bundle="${bundle }" var="type"></fmt:message>
<fmt:message key="locale.searchrslt" bundle="${bundle }" var="rslt"></fmt:message>
<fmt:message key="locale.description" bundle="${bundle }"
	var="description"></fmt:message>
<fmt:message key="locale.no_book_found" bundle="${bundle }"
	var="book_errmsg"></fmt:message>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link href="./css/style.css" rel="stylesheet">
<script src="./js/main.js"></script>
</head>
<body>
	<div class="main">
		<jsp:include page="../templates/header.jsp" />
		<c:if test="${error == true }">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
				<strong>ERROR!</strong> <span>"${book_errmsg }"</span>
			</div>
		</c:if>
		<c:if test="${error == false }">
			<div class="container" style="padding-top: 100px">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3>${rslt }</h3>
					</div>
					<div class="panel-body">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th>${book_id }</th>
									<th>${book_title }</th>
									<th>${author }</th>
									<th>${info }</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${searchResult}" var="book">
									<tr>
										<td>${book.id}</td>
										<td>${book.title}</td>
										<td>${book.author}</td>
										<td><a href="command?name=book&id=${book.id}"><button
													class="btn btn-default" type="submit" name="button">Get
													More Info.</button></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>