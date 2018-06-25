<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='pfqApp'>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<body ng-controller='CategoryController'>

	<jsp:include page="/WEB-INF/includes/navmenu.jsp"></jsp:include>

	<main role="main" class="container">
	<div class="jumbotron">
		<h1>
			<c:out value="${msg}" />
		</h1>
		<base-url model="baseUrl" url="${pageContext.request.contextPath}"></base-url>
		{{baseUrl}}
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a type="button" href="<c:url value="/category/add" />"
				class="btn  btn-outline-success"> <i class="fas fa-plus-circle"></i>
				<span></span>
			</a>
		</div>
		</nav>
		<div >
			<div infinite-scroll='listcategory.nextPage()'
				infinite-scroll-disabled='listcategory.busy'
				infinite-scroll-distance='1'>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat='item in listcategory.items'>
							<th scope="row">{{$index}}</th>
							<td>{{item.name}}</td>
							<td><a href="{{baseUrl}}/category/{{item.id}}"
								type="button" class="btn btn-primary ">Open</a></td>
						</tr>

					</tbody>
				</table>

				<div ng-show='listcategory.busy'>Loading data...</div>
			</div>
		</div>
	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>