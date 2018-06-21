<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app='pfqApp'>
<jsp:include page="/WEB-INF/includes/head.jsp"></jsp:include>
<body>
	<div class="container">
		<div class="masthead">
			<h3 class="text-muted">
				<c:out value="${title}" />
			</h3>
			<jsp:include page="/WEB-INF/includes/navmenu.jsp"></jsp:include>
		</div>

		<div class="row"></div>

		<div class="jumbotron">
			<h1>
				<c:out value="${msg}" />
			</h1>
			<div  ng-controller='CompanyController'>
				<div infinite-scroll='listcompany.nextPage()'
					infinite-scroll-disabled='listcompany.busy'
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


							<tr ng-repeat='item in listcompany.items'>
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
											{{value}}
									</div>
								</td>
							</tr>

						</tbody>
					</table>

					<div ng-show='listcompany.busy'>Loading data...</div>
				</div>
			</div>
			<p></p>
		</div>

		<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>