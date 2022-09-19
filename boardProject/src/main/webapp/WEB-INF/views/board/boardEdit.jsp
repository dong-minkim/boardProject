<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<h2>게시글 수정</h2>
	<form action="/edit/${board.id}" , method="post">
		<input type="hidden" name="pageNum" value="${pageNum }">
		<input type="hidden" name="pageAmount" value="${pageAmount}" >
 		<table border="1" width="1000px">
			<caption>
				<strong>* 표시: 수정 가능</strong>
			</caption>

			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<input type="hidden" name="id" id="id" value="${board.id }" />
			<tr>
				<th>제목 *</th>
				<td><input type="text" style="width: 300px; border: none"
					name="title" id="title" value="${board.title }" required />
			</tr>
			<tr>
				<th>작성자</th>
				<td><strong>${board.writer }</strong>
			</tr>
			<tr height="300px">
				<th>내용*</th>
				<td><textarea style="border: none;" rows="40" cols="120" name="content" >${board.content }</textarea>
			</tr>
		</table>
		<div>
			<input type="button" value="목록" onclick="location.href='/list?pageNum=${pageNum }&pageAmount=${pageAmount}'">
			<input type="reset" value="초기화" /> 
			<input type="submit" value="수정" />
		</div>
	</form>
</body>
</html>