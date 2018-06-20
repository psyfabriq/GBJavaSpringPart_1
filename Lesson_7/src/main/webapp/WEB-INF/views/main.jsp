<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
			<div ng-app='pfqApp' ng-controller='MainController'>
				<div infinite-scroll='listtask.nextPage()'
					infinite-scroll-disabled='listtask.busy' infinite-scroll-distance='1'>
					<div ng-repeat='item in listtask.items'>
						<span class='score'>{{item.score}}</span> <span class='title'>
							<a ng-href='{{listtask.url}}' target='_blank'>{{item.title}}</a>
						</span> <small>by {{item.author}} - <a
							ng-href='http://reddit.com{{listtask.permalink}}' target='_blank'>{{item.num_comments}}
								comments</a>
						</small>
						<div style='clear: both;'></div>
					</div>
					<div ng-show='listtask.busy'>Loading data...</div>
				</div>
			</div>
			<p></p>
		</div>

		<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>