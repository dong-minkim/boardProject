<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>

<style>
	h1, h2{
		text-align: center;
	}
	table{
		margin: auto;
		
	}
</style>

</head>
<body>
	<h1>게시판 목록</h1>
	<br/>
	<h2><c:if test="${pageMaker.searchWord!='' }">"${pageMaker.searchWord }" 검색 결과</c:if></h2>
	<table width="930px">
		<tr>
			<td align="right"><input type="button" value="글 작성" onclick="location.href='/board/regist'">
		</tr>
	</table>
	<table border="1" width="930px">
		<tr>
			<th width="50px">No</th>
			<th width="400px">제목</th>
			<th width="80px">작성자</th>
			<th width="80px">조회수</th>
			<th width="160px">작성일</th>
			<th width="160px">수정일</th>
		</tr>
		<c:forEach var="boardList" items="${boardList }">
			<tr>
				<td>${boardList.id }</td>
				<td><a href="/board/detail/${boardList.id }?searchType=${pageMaker.searchType }&searchWord=${pageMaker.searchWord }&pageNum=${pageMaker.pageNum}&pageAmount=${pageMaker.pageAmount}">
					${boardList.title }<b> [<c:out value="${boardList.reply_cnt }" />]</b></a></td>
				<td>${boardList.writer }</td>
				<td>${boardList.views }</td>
				<td>${boardList.regist_datetime }</td>
				<td>${boardList.modify_datetime }</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	
	<div align="center">
		<c:if test="${pageMaker.prev }">
			<a href="/board/list?searchType=${pageMaker.searchType }&searchWord=${pageMaker.searchWord }&pageNum=${pageMaker.startPage - 1 }&pageAmount=${pageMaker.pageAmount }">&lt;</a>
		</c:if>
		
		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
			<c:if test="${num == pageMaker.pageNum }">
				<b>${num }</b>
			</c:if>
			<c:if test="${num != pageMaker.pageNum }">
				<a href="/board/list?searchType=${pageMaker.searchType }&searchWord=${pageMaker.searchWord }&pageNum=${num }&pageAmount=${pageMaker.pageAmount}">${num }</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${pageMaker.next }">
			<a href="/board/list?searchType=${pageMaker.searchType }&searchWord=${pageMaker.searchWord }&pageNum=${pageMaker.endPage + 1 }&pageAmount=${pageMaker.pageAmount }">&gt;</a>
		</c:if>
	</div>
	
	<br/>
	
	<form action="/board/list" method="get" style="text-align: center;">
		<div>
			<select  name="searchType">
				<option value="" <c:out value="${pageMaker.searchType==''? 'selected':'' }" />>all</option>
				<option value="title" <c:out value="${pageMaker.searchType=='title'? 'selected':'' }" />>제목</option>
				<option value="content" <c:out value="${pageMaker.searchType=='content'? 'selected':'' }" />>내용</option>
				<option value="writer" <c:out value="${pageMaker.searchType=='writer'? 'selected':'' }" />>작성자</option>
				<option value="tc" <c:out value="${pageMaker.searchType=='tc'? 'selected':'' }" />>제목+내용</option>
			</select>
			<input type="text" name="searchWord" value="${pageMaker.searchWord }">
			<input type="hidden" name="pageNum" value="${pageMaker.pageNum }">
			<input type="hidden" name="pageAmount" value="${pageMaker.pageAmount }">
			<input type="submit" value="검색">
		</div>
	</form>
	
	
</body>
</html>