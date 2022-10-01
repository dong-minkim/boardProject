<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h1>로그인</h1>
			<h2><c:out value="${error }"/></h2>
			<h2><c:out value="${logout}"/></h2>
		</div>
		<div>
			<form role="form" method='post' action="/login">
				<fieldset>
					<div>
						<input type="text" placeholder="userid" name="username" autofocus>
					</div>
					<div>
						<input type="password" placeholder="Password" name="password" value="">
					</div>
					<div>
						<input type="submit" />
					</div>
<!-- 					<div class="checkbox"> -->
<!-- 						<label> <input name="remember-me" type="checkbox">Remember Me -->
<!-- 						</label> -->
<!-- 					</div> -->
<!-- 					Change this to a button or input when using this as a form -->
<!-- 					<a href="index.html" class="btn-success">Login</a> -->
				</fieldset>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</div>
	
	
  <script>
  $(".btn-success").on("click", function(e){
    
    e.preventDefault();
    $("form").submit();
    
  });
  </script>
  
<c:if test="${param.logout != null}">
      <script>
      $(document).ready(function(){
      	alert("로그아웃하였습니다.");
      });
      </script>
</c:if>  

</body>

</html>
