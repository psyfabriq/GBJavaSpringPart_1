<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='pfqApp'>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<body ng-controller='CompanyController'>

	<jsp:include page="/WEB-INF/includes/navmenu.jsp"></jsp:include>

	<main role="main" class="container">
	<div class="jumbotron">
		<h1>
			<c:out value="${msg}" />
		</h1>
		<base-url model="baseUrl" url="${pageContext.request.contextPath}"></base-url>	
     	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a type="button" href="<c:url value="/company/add" />"
				class="btn  btn-outline-success"> <i class="fas fa-plus-circle"></i>
				<span></span>
			</a>
		</div>
		</nav>
		<div >
			<div infinite-scroll='listcompany.nextPage()'
				infinite-scroll-disabled='listcompany.busy'
				infinite-scroll-distance='1'>

				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Address</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>


						<tr ng-repeat='item in listcompany.items'>
							<th scope="row">{{$index}}</th>
							<td>{{item.name}}</td>
							<td>{{item.address}}</td>
							<td><a href="{{baseUrl}}/company/{{item.id}}"
								type="button" class="btn btn-primary ">Open</a></td>
						</tr>

					</tbody>
				</table>

				<div ng-show='listcompany.busy'>Loading data...</div>
			</div>
		</div>
	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>