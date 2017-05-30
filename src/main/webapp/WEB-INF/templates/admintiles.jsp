<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}"
	scope="session" />
<fmt:setLocale value="${language }" scope="session" />
<fmt:setBundle basename="resource_bundle/locale" var="bundle"
	scope="session" />

<div class="container-2">
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="circle-tile">
					<a href="#">
						<div class="circle-tile-heading dark-blue">
							<i class="fa fa-users fa-fw fa-3x"></i>
						</div>
					</a>
					<div class="circle-tile-content dark-blue">
						<div class="circle-tile-description text-faded">Users</div>
						<div class="circle-tile-number text-faded">
							${totalUsers } <span id="sparklineA"></span>
						</div>
						<a href="#" class="circle-tile-footer">More Info <i
							class="fa fa-chevron-circle-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="circle-tile">
					<a href="#">
						<div class="circle-tile-heading orange">
							<i class="fa fa-book fa-fw fa-3x"></i>
						</div>
					</a>
					<div class="circle-tile-content orange">
						<div class="circle-tile-description text-faded">Books</div>
						<div class="circle-tile-number text-faded">${totalBooks } Total</div>
						<a href="#" class="circle-tile-footer">More Info <i
							class="fa fa-chevron-circle-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="circle-tile">
					<a href="#">
						<div class="circle-tile-heading blue">
							<i class="fa fa-bookmark fa-fw fa-3x"></i>
						</div>
					</a>
					<div class="circle-tile-content blue">
						<div class="circle-tile-description text-faded">Most Popular
							Book</div>
						<div class="circle-tile-number text-faded">
							${topBook.title } <span id="sparklineB"></span>
						</div>
						<a href="#" class="circle-tile-footer">More Info <i
							class="fa fa-chevron-circle-right"></i></a>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-6">
				<div class="circle-tile">
					<a href="#">
						<div class="circle-tile-heading red">
							<i class="fa fa-shopping-cart fa-fw fa-3x"></i>
						</div>
					</a>
					<div class="circle-tile-content red">
						<div class="circle-tile-description text-faded">Orders</div>
						<div class="circle-tile-number text-faded">
							24 <span id="sparklineC"></span>
						</div>
						<a href="#" class="circle-tile-footer">More Info <i
							class="fa fa-chevron-circle-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- page-wrapper END-->
</div>