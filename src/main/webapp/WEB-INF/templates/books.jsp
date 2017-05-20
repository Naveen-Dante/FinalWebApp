<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="locale.lib" bundle="${bundle }" var="title"></fmt:message>
<fmt:message key="locale.book_id" bundle="${bundle }" var="book_id"></fmt:message>
<fmt:message key="locale.title" bundle="${bundle }" var="book_title"></fmt:message>
<fmt:message key="locale.author" bundle="${bundle }" var="author"></fmt:message>
<fmt:message key="locale.information" bundle="${bundle }" var="info"></fmt:message>
<div class="center-table" id="books_table">
	<div class="panel panel-success">
		<div class="panel-heading">
			<c:if test="${arefavs == true }">
				<h3>${favs }</h3>
			</c:if>
			<c:if test="${areFavs == null || areFavs == false }">
				<h3>${title }</h3>
			</c:if>
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
					<c:forEach items="${books}" var="book">
						<tr>
							<td>${book.id}</td>
							<td>${book.title}</td>
							<td>${book.author}</td>
							<td><a href="command?name=book&id=${book.id}"><button class="btn btn-default" type="submit"
									name="button">Get More Info.</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>