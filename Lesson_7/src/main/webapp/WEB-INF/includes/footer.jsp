<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Footer-->
<footer class="footer">
	<!--Footer Links-->
	<div class="container-fluid text-center text-md-left">
		<div class="row"></div>
	</div>
	<div class="footer-copyright py-3 text-center">
		© 2018 Copyright: <a
			href="https://github.com/psyfabriq/GBJavaSpringPart_1"> PsyFabriQ
			DEV</a>
	</div>
</footer>

<c:forEach items="${js}" var="url">
	<script type="text/javascript" src="<c:url value="${url}" />"></script>
</c:forEach>


