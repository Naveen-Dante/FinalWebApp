<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="sidebar-wrapper" class="sidenav">
	<ul id="sidebar_menu" class="sidebar-nav">
		<li class="sidebar-brand"><a id="menu-toggle">${user.firstName }<span
				id="main_icon" class="glyphicon glyphicon-align-justify"></span></a></li>
	</ul>
	<ul class="sidebar-nav" id="sidebar">
		<li><a href=/admin?command=home>Home<span class="sub_icon glyphicon glyphicon-home"></span></a></li>
		<li><a href=/admin?command=books>Books<span class="sub_icon glyphicon glyphicon-book"></span></a></li>
		<li><a href=/admin?command=users>Users<span class="sub_icon glyphicon glyphicon-user"></span></a></li>
		<li><a href="/admin?command=logout">Logout<span class="sub_icon glyphicon glyphicon-off"></span></a></li>
	</ul>
</div>
