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
	<form action="/regist", method = "post">
	<table border="1" width="1000px">
		<caption><strong>* 표시는 필수입력</strong></caption>
		
		<colgroup>
			<col width="20%">
			<col width="80%">
		</colgroup>
		
		<tr>
			<th>제목 *</th>
			<td><input type="text" style="width:300px;border:none" name="title" id="title" required />
		</tr>
		<tr>
			<th>작성자 *</th>
			<td><input type="text" style="width:300px;border:none" name="writer" id="writer" required />
		</tr>
		<tr height = "300px">
			<th>내용</th>
			<td><textarea style="border: none;" rows="40" cols="120" name="content"></textarea>
		</tr>
	</table>
	<table width="1000px">
		<tr>
			<td align="right"><input type="button" value="목록" onclick="location.href='/list'">
		<input type="reset" value="초기화" />
		<input type="submit" value="등록" /></td>
		</tr>
	</table>
	</form>
</body>
</html>