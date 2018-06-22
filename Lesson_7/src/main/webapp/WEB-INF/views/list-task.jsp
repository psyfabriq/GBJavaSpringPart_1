<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='pfqApp'>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/includes/navmenu.jsp"></jsp:include>

	<div class="wrapper" ng-controller='TaskController'>
		<jsp:include page="/WEB-INF/includes/sidebar.jsp"></jsp:include>
		<!-- Page Content  -->
		<main role="main" class="container-fluid">

		<div class="jumbotron">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> <span></span>
				</button>
				<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
					type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-align-justify"></i>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="nav navbar-nav ml-auto">
						<li class="nav-item active"><a class="nav-link" href="#">Page</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#">Page</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Page</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Page</a></li>
					</ul>
				</div>
			</div>
			</nav>
			<h1>
				<c:out value="${msg}" />
			</h1>
			<div>
				<div infinite-scroll='listtask.nextPage()'
					infinite-scroll-disabled='listtask.busy'
					infinite-scroll-distance='1'>

					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Active</th>
								<th scope="col">Name</th>
								<th scope="col">Publish</th>
								<th scope="col">End</th>
								<th scope="col">Company</th>
								<th scope="col">Category</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat='item in listtask.items'>
								<th scope="row">{{$index}}</th>
								<td>{{item.active}}</td>
								<td>{{item.name}}</td>
								<td>{{item.publishedDate}}</td>
								<td>{{item.endDate}}</td>
								<td>
									<div ng-repeat="(index,value) in item.ownerInfo">
										{{value}}</div>
								</td>
								<td>
									<div ng-repeat="(index,value) in item.categoryInfo">
										{{value}}</div>
								</td>
							</tr>

						</tbody>
					</table>
					<div ng-show='listtask.busy'>Loading data...</div>
				</div>
			</div>
		</div>
			<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
		
		</main>
	</div>


</body>
</html>