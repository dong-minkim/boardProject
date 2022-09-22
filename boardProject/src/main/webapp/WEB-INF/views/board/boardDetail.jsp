<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	h1, h2{
		text-align: center;
	}
	table{
		margin: auto;
		
	}
</style>
<title>게시글(${board.id })</title>
</head>
<body>
	<h1>게시글(${board.id }) ${pageNum }</h1>
	<table width="930px">
		<tr>
			<td align="left">
<%-- 			<form value = "목록" action="/list?pageNum=${pageMaker.pageNum }&pageAmount=${pageMaker.pageAmount}"></form> --%>
			<input type="button" value="목록" onclick="location.href='/board/list?searchType=${searchType }&searchWord=${searchWord }&pageNum=${pageNum }&pageAmount=${pageAmount}'"></td>
			<td align="right"><div><input type="button" value="수정" onclick="location.href='/board/edit/${board.id}?pageNum=${pageNum }&pageAmount=${pageAmount}'">
								   <input type="button" value="삭제" onclick="location.href='/board/delete/${board.id}?pageNum=${pageNum }&pageAmount=${pageAmount}'"></div></td>
		</tr>
	</table>
	<table border="1" width="930px">
		<tr>
			<td width="700px"><b>${board.title }</b></td>
			<td align="left"><div>등록: ${board.regist_datetime }<br/>
			수정: ${board.modify_datetime }</div></td>		
		</tr>
		<tr>
			<td align="left" width="700px">${board.writer }</td>
			<td>조회 수 ${board.views }</td>
		</tr>
		<tr height="500px">
			<td colspan="2" valign="top">${board.content }</td>
		</tr>
	</table>
	
	
	<!-- 댓글 -->
	<script type = "text/javascript" src="/resources/js/reply.js"></script>
	
	<script type = "text/javascript">
	console.log("===========");
	console.log("JS TEST");
	
	var boardId = '<c:out value="${board.id}"/>';
	
	//add
// 	replyService.add(
// 			{
// 				board_id:boardId, 
// 				reply_content:"JS Test5", 
// 				reply_writer:"tester5"
// 			},

// 			function(result){
// 				alert("RESULT: " + result);	
// 			}
// 	);

	//list
// 	replyService.getList(
// 		{board_id:boardId}, function(list){
// 			//for(var i=0, len=list.length||0; i<len; i++)
// 			for(var i=0, len=list.length; i<len; i++){
// 				console.log(list[i]);
// 			}
// 		}	
// 	);
	
	//update
	replyService.update(
		{reply_id : 3, reply_content : "modify reply ....", reply_writer : "nonTester"},
		function(result) {
			alert("modify success...");
		}
	);
		
// 	delete
// 	replyService.remove(2, function(count) {
		
// 		console.log(count);
		
// 		if(count==="success"){
// 			alert("REMOVED");
// 		}
// 	}, function(err){
// 		alert('ERROR...');
// 	});

	</script>
	
</body>
</html>