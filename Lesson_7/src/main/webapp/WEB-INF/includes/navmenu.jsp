<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#"><c:out value="${projectname}" /></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<c:forEach items="${navigatemenu}" var="item">
			<li class="nav-item "><a class="nav-link" href="<c:url value="${item.url}" />"><c:out value="${item.name}"/></a></li>
			</c:forEach>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<select class="selectpicker" data-width="fit">
				<option
					data-content='<span class="flag-icon flag-icon-us"></span> English'>English</option>
				<option
					data-content='<span class="flag-icon flag-icon-mx"></span> Español'>Español</option>
			</select> 
		</form>
	</div>
</nav>