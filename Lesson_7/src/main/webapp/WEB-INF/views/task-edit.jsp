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

		<form:form method="POST"
			action="${pageContext.request.contextPath}${postUrl}"
			modelAttribute="task">


			<div class="form-group">
				<form:label for="usr" path="name">Name:</form:label>
				<form:input type="text" path="name" class="form-control" id="usr" />
			</div>
			<div class="form-group">
				<form:label for="pd" path="publishedDate">Publish Date:</form:label>
				<form:input type="text" path="publishedDate" class="form-control" id="pd" />
			</div>
			<div class="form-group">
				<form:label for="ed" path="endDate">End Date:</form:label>
				<form:input type="text" path="endDate" class="form-control" id="ed" />
			</div>
			<div class="form-group">
				<form:label for="own" path="owner">Owner:</form:label>
				<form:input type="text" path="owner" class="form-control" id="own" />
			</div>
			<input type="submit" class="btn btn-info" value="${submitTitle}" />
			

		</form:form>

	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>