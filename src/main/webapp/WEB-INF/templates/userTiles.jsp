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

<div class="container tilewrapper" >
  
  <div class="row">
    <div class="col-sm-6">
      <div class="tile purple">
        <h3 class="title">User Details</h3>
        
      </div>
    </div>
    <div class="col-sm-4">
     <a href="/command?name=books"> <div class="tile red">
        <h3 class="title">Book Details</h3>
        	
        </div></a>
    </div>
    
  </div>
  
</div>