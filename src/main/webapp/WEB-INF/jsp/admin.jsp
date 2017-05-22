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
<title>Admin</title>
</head>
<body>
	
</body>
</html>