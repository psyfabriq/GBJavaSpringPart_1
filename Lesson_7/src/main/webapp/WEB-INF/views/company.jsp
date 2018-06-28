<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='pfqApp'>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<body>

	<jsp:include page="/WEB-INF/includes/navmenu.jsp"></jsp:include>

	<main role="main" class="container">
	<div class="jumbotron">
		<h1>
			<c:out value="${msg}" />
		</h1>

		<div class="card">
			<div class="card-body">
				<h4 class="card-title"><c:out value="${company.name}" /></h4>
				<p class="card-text"><c:out value="${company.address}" /></p>
				<p class="card-text"><c:out value="${company.description}" /></p>
				<a href="<c:url value="${edit}" />" class="card-link">Edit</a>
				<form:form method="POST" action="${pageContext.request.contextPath}${delete}" modelAttribute="company">
				    <form:hidden path="id"></form:hidden>
				    <input type="submit" class="card-link" value="Delete" />
				</form:form>
			</div>
		</div>

	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>