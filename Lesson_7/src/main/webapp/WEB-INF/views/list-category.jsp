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
		<div ng-controller='CategoryController'>
			<div infinite-scroll='listcategory.nextPage()'
				infinite-scroll-disabled='listcategory.busy'
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


						<tr ng-repeat='item in listcategory.items'>
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

				<div ng-show='listcategory.busy'>Loading data...</div>
			</div>
		</div>
	</div>
	</main>

	<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>

</body>
</html>