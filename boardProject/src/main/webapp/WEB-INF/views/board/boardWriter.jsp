<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<h2>게시글 작성</h2>
	<form action="/regist">
	<table border="1" width="100%">
		<caption><strong>* 표시는 필수입력</strong></caption>
		
		<colgroup>
			<col width="20%">
			<col width="80%">
		</colgroup>
		
		<tr>
			<th>제목 *</th>
			<td><input type="text" name="title" id="title" required />
		</tr>
		<tr>
			<th>작성자 *</th>
			<td><input type="text" name="writer" id="writer" required />
		</tr>
		<tr height = "300px">
			<th>내용</th>
			<td><input type="text" name="content" id="content" />
		</tr>
	</table>
	<div>
		<input type="button" value="목록" onclick="location.href='/list'">
		<input type="reset" value="초기화" />
		<input type="submit" value="등록" />
	</div>
	</form>
</body>
</html>