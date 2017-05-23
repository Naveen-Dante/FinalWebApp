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
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="./css/admin.css" rel="stylesheet">
<script src="./js/admin.js"></script>
<title>Admin</title>
</head>
<body lang="${language }" style="background-color: rgba(0,0,0,0.85); color: white">
	<jsp:include page="../templates/adminNavbar.jsp"></jsp:include>
	<div class="main">
		<h1 style="font-weight: bold;">DashBoard<span style="font-size: 18px; font-weight: lighter;"> Control Panel</span></h1>
		
	</div>
	
</body>
</html>