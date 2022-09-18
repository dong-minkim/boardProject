<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글(${board.id })</title>
</head>
<body>
	<div>
		<input type="button" value="목록" onclick="location.href='/list'">
		<input type="button" value="수정" onclick="location.href='/edit/${board.id}'">
	</div>
	<table border="1" width="1000px">
		<tr>
			<td width="700px"><b>${board.title }</b></td>
			<td align="right">${board.regist_datetime }</td>		
		</tr>
		<tr>
			<td align="left" width="700px">${board.writer }</td>
			<td>조회 수 ${board.views }</td>
		</tr>
		<tr height="500px">
			<td colspan="2" valign="top">${board.content }</td>
		</tr>
	</table>
</body>
</html>