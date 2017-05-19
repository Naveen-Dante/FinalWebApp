<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="locale" var="bundle" scope="session" />
<fmt:message key="locale.welcome" var="welcome" bundle="${bundle }"/>


<!DOCTYPE HTML>
<html>
<head>
<link href="./css/style.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="./js/main.js"></script>
<base href="/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<jsp:include page="./WEB-INF/templates/header.jsp" />
	<h1>${welcome}</h1>
</body>
</html>
