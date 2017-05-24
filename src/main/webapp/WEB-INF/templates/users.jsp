<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="locale.lib" bundle="${bundle }" var="title"></fmt:message>
<fmt:message key="locale.book_id" bundle="${bundle }" var="book_id"></fmt:message>
<fmt:message key="locale.title" bundle="${bundle }" var="book_title"></fmt:message>
<fmt:message key="locale.author" bundle="${bundle }" var="author"></fmt:message>
<fmt:message key="locale.information" bundle="${bundle }" var="info"></fmt:message>
<div>
	<div class="col-lg-3 col-md-3 col-sm-6">
		<h2>USERS</h2>
	</div>
</div>
<style>
.sidebar-nav li a:hover {
	color: #fff;
	background: rgba(255, 255, 255, 0.2);
	text-decoration: none;
	cursor: pointer;
}
</style>
<div class="container">
	<table class="table table-bordered table-inverse">
		<thead>
			<tr>
				<th>Name</th>
				<th>User Name</th>
				<th>Email ID</th>
				<th>Phone Number</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allUser}" var="user">
				<tr>
					<td>${user.firstName} ${user.lastName}</td>
					<td>${user.userName}</td>
					<td>${user.email}</td>
					<td>${user.phoneNumber}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>