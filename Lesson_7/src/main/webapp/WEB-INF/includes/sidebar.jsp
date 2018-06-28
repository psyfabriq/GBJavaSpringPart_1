<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Sidebar  -->
<nav id="sidebar">
	<div class="sidebar-header"></div>

	<ul class="list-unstyled components">
		<li><strong>Categories</strong> <select
			ng-model="filterData.selectedCategory" class="form-control" multiple="multiple">
				<c:if test="${not empty categories}">
					<option value="0" selected ng-click="updateCategory()">All
						Categories</option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</c:if>
		</select></li>

		<li><strong>Companies</strong> <select
			ng-model="filterData.selectedCompany" class="form-control"
			multiple="multiple">
				<c:if test="${not empty companes}">
					<option value="0" selected ng-click="updateCompany();">All
						Companes</option>
					<c:forEach items="${companes}" var="company">
						<option value="${company.id}">${company.name}</option>
					</c:forEach>
				</c:if>
		</select></li>

		<button type="button" class="btn btn-bloc btn-info"
			ng-click="seFilter();">Filter</button>
	</ul>

</nav>