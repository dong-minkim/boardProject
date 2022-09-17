<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table width="1200px">
		<tr>
			<td align="right"><input type="button" value="글 작성" onclick="location.href='/regist'">
		</tr>
	</table>
	<table border="1" width="1200px">
		<tr>
			<th width="50px">No</th>
			<th width="650px">제목</th>
			<th width="90px">작성자</th>
			<th width="10">조회수</th>
			<th width="200px">작성일</th>
			<th width="200px">수정일</th>
		</tr>
		<c:forEach var="boardList" items="${boardList }">
			<tr>
				<td>${boardList.id }</td>
				<td><a href="/detail/${boardList.id }">${boardList.title }</a></td>
				<td>${boardList.writer }</td>
				<td>${boardList.views }</td>
				<td>${boardList.regist_datetime }</td>
				<td>${boardList.modify_datetime }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>