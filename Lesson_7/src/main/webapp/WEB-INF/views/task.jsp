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
				<h4 class="card-title">
					${task.name}
				</h4>
				<p class="card-text">
					<c:out value="${task.publishedDate}" />
				</p>
				<p class="card-text">
					<c:out value="${task.endDate}" />
				</p>
				<p class="card-text">
					<c:out value="${task.active}" />
				</p>
				<p class="card-text">
					<c:out value="${task.owner.name}" />
				</p>
				<c:forEach items="${task.category}" var="category">
					<p class="card-text">
						<c:out value="${category.name}" />
					</p>
				</c:forEach>
				<a href="<c:url value="${edit}"/>" class="card-link">Edit</a>

				<form:form method="POST" action="${pageContext.request.contextPath}${delete}" modelAttribute="task">
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