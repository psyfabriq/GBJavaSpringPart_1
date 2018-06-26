<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
					<c:out value="${name}" />
				</h4>
				<p class="card-text">
					<c:out value="${published_date}" />
				</p>
				<p class="card-text">
					<c:out value="${end_date}" />
				</p>
				<p class="card-text">
					<c:out value="${active}" />
				</p>
				<p class="card-text">
					<c:out value="${owner.name}" />
				</p>
				<c:forEach items="${categories}" var="category">
					<p class="card-text">
						<c:out value="${category.name}" />
					</p>
				</c:forEach>
				<a href="<c:url value="${edit}" />" class="card-link">Edit</a> <a
					href="<c:url value="${delete}" />" class="card-link">Delete</a>
			</div>
		</div>


	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>