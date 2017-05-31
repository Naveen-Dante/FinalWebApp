<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}"
	scope="session" />
<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="resource_bundle/locale" var="bundle"
	scope="session" />
<fmt:message key="locale.welcome" var="welcome" bundle="${bundle }" />

<!DOCTYPE HTML>
<html lang=${language }>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${sessionScope.get('user') == null}">
    <c:redirect url="/"/>
</c:if>
<title>Books</title>
<link href="./css/style.css" rel="stylesheet">
<script src="./js/main.js"></script>
</head>
<body>
	<div class="main">
		<jsp:include page="../templates/header.jsp" />
		<c:if test="${error == true }">
			<div class="alert alert-danger alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
				<strong>ERROR!</strong> <span>"${error_message }"</span>
			</div>
		</c:if>
		<c:if test="${success_message != null }">
			<div class="alert alert-success alert-dismissable fade in">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">x</a>
				<strong>Success!</strong> <span>"${success_message }"</span>
			</div>
		</c:if>
		<jsp:include page="../templates/favbooks.jsp"></jsp:include>
	</div>
</body>
</html>
