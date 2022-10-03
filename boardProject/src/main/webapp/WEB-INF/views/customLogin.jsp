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

	<div style="width:400px; margin:auto">
	<h1>로그인</h1>
		<div style="margin:auto">
			<h2><c:out value="${error }"/></h2>
			<h2><c:out value="${logout}"/></h2>
		</div>
		<div style="margin:auto">
			<form method="post" action="/login">
				<table border="1"  style="width: 400px; margin:auto">
					<tr>
						<td colspan="3" align="right"><input type="button" value="목록" onclick="location.href='/board/list'"></td>
					</tr>
				</table>
				<fieldset>
					<table border="1" style="width: 320px; margin:auto">
						<tr>
							<td align="right"><label style="width: 100px">ID:</label>
							<td colspan="2"><input type="text" name="username" placeholder="userid" style="width:300px" autofocus></td>
						</tr>
						<tr>
							<td align="right"><label style="width: 100px">PW:</label>
							<td colspan="2"><input type="password" name="password" placeholder="password" style="width: 300px" value=""></td>
						</tr>
						<tr>
							<td></td>
							<td align="left"><input type="checkbox" name="remember-me"/>로그인 저장</td>
							<td align="right"><input type="submit" value="로그인" /></td>
						</tr>
					</table>
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
