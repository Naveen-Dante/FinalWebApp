<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:message key="locale.lib" bundle="${bundle }" var="title"></fmt:message>
<fmt:message key="locale.book_id" bundle="${bundle }" var="book_id"></fmt:message>
<fmt:message key="locale.title" bundle="${bundle }" var="book_title"></fmt:message>
<fmt:message key="locale.author" bundle="${bundle }" var="author"></fmt:message>
<fmt:message key="locale.information" bundle="${bundle }" var="info"></fmt:message>
<fmt:message key="locale.favs" bundle="${bundle }" var="favs"></fmt:message>
<div class="center-table" id="books_table">
	<h2 style="align: center; padding-left: 250px">Books</h2>
	<table class="table table-hover ">
		<thead>
			<tr>
				<th>${book_id }</th>
				<th>${book_title }</th>
				<th>${author }</th>
				<th>${info }</th>
				<th>${favs }</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td><a href="/command?name=book&id=${book.id}"><button
								class="btn btn-default" type="submit" name="button">Get
								More Info.</button></a></td>
					<td>
						<c:choose>
							<c:when test="${book.favourite == false }">
								<a href="/FinalWebApp/command?name=addfavs&id=${book.id}"><button class="btn btn-sm btn-info" type="button"><span class="glyphicon glyphicon-plus">
								</span> Add</button></a>
							</c:when>
							<c:otherwise>
								<a><button class="btn btn-sm btn-info" type="button" disabled><span class="glyphicon glyphicon-plus">
								</span> Added</button></a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>