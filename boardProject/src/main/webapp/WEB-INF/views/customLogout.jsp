<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Title</title>
</head>

<body>

	<div>
		<div>
			<h1>로그아웃</h1>
		</div>
		<div>
			<form role="form" method="post" action="/customLogout">
<!-- 				<fieldset> -->
<!-- 					Change this to a button or input when using this as a form -->
<!-- 					<a href="index.html" class="btn-success">Logout</a> -->
<!-- 				</fieldset> -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit">로그아웃</button>
			</form>
		</div>
	</div>


	<script>
	$(".btn-success").on("click", function(e){
		
		e.preventDefault();
		$("form").submit();
		
	});
	</script>

</body>
</html>