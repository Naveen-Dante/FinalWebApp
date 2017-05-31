<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="locale.lib" bundle="${bundle }" var="title"></fmt:message>
<fmt:message key="locale.book_id" bundle="${bundle }" var="book_id"></fmt:message>
<fmt:message key="locale.title" bundle="${bundle }" var="book_title"></fmt:message>
<fmt:message key="locale.author" bundle="${bundle }" var="author"></fmt:message>
<fmt:message key="locale.information" bundle="${bundle }" var="info"></fmt:message>
<div>
	<div class ="col-lg-3 col-md-3 col-sm-6"><h2>Books</h2></div>
	<div class ="col-lg-9 col-md-9 col-sm-6">
		<button class="btn btn-md btn-default pull-right" id="newBookBtn">
			ADD NEW BOOK
		</button>
	</div>
</div>
<style>
.sidebar-nav li a:hover {
    color: #fff;
    background: rgba(255,255,255,0.2);
    text-decoration: none;
    cursor: pointer;
}</style>
<table class="table table-bordered table-inverse">
	<thead>
		<tr>
			<th>${book_id }</th>
			<th>${book_title }</th>
			<th>${author }</th>
			<th>Language</th>
			<th>Information</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${allBooks}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.language }</td>
				<td><a href="/admin?command=book&id=${book.id}&language=${book.language}"><button
							class="btn btn-default" type="submit" name="button">Get
							More Info.</button></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="modal fade" id="newBook" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content" style="color:black;">
			<div class="modal-header modal-header-info"
				style="padding: 10px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>
					<span class="glyphicon glyphicon-book" ></span> Add Book
				</h4>
			</div>
			<div class="modal-body" style="padding: 30px 50px;">
				<form class="form-horizontal" action="admin?command=new-book"
					method="post">
					<div class="form-group">
						<label class="control-label col-sm-4" for="Title">Title: </label>
						<div class="col-sm-8" data-toggle="tooltip"
							title="Enter your First Name.">
							<input type="text" class="form-control" name="title"
								placeholder="Enter Title" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Author">Author:
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="author"
								placeholder="Enter Author" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="Image URL">Image
							URL: </label>
						<div class="col-sm-8">
							<input type="url" class="form-control" name="imageurl"
								placeholder="Enter Image URL" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="language">Language:
						</label>
						<div class="col-sm-8">
							<select class="form-control" id="select-language"
								name="select-language">
								<option>ENGLISH</option>
								<option>ESPANOL</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="type">Type: </label>
						<div class="col-sm-8">
							<label class="radio-inline"> <input type="radio"
								name="opttype">EBOOK
							</label> <label class="radio-inline"> <input type="radio"
								name="opttype">PAPER
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="description">Description: </label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="4" name="description"></textarea>
						</div>
					</div>
					<button type="submit" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-off"></span>Add
					</button>

				</form>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-danger btn-default pull-left"
					data-dismiss="modal">
					<span class="glyphicon glyphicon-remove"></span> Cancel
				</button>
			</div>
		</div>
	</div>
</div>