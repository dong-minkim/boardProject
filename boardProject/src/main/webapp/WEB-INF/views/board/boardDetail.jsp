<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
h1, h2 {
	text-align: center;
}

table {
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
				<input type="button" value="목록"
				onclick="location.href='/board/list?searchType=${searchType }&searchWord=${searchWord }&pageNum=${pageNum }&pageAmount=${pageAmount}'">
			</td>
			<td align="right"><div>
					<input type="button" value="수정"
						onclick="location.href='/board/edit/${board.id}?pageNum=${pageNum }&pageAmount=${pageAmount}'">
					<input type="button" value="삭제"
						onclick="location.href='/board/delete/${board.id}?pageNum=${pageNum }&pageAmount=${pageAmount}'">
				</div></td>
		</tr>
	</table>
	<table border="1" width="930px">
		<tr>
			<td width="700px"><b>${board.title }</b></td>
			<td align="left"><div>
					등록: ${board.regist_datetime }<br /> 수정: ${board.modify_datetime }
				</div></td>
		</tr>
		<tr>
			<td align="left" width="700px">${board.writer }</td>
			<td>조회 수 ${board.views }</td>
		</tr>
		<tr height="500px">
			<td colspan="2" valign="top">${board.content }</td>
		</tr>
	</table>


	<br />
	<br />

	<div style="width: 930px; margin: auto;">
		<div
			style="height: 30px; background-color: silver; vertical-align: middle;">
			<strong>Reply</strong>
		</div>
		<div>
			<ul class="chat">
			</ul>
		</div>
	</div>

	<!-- 댓글 -->
	<script type="text/javascript" src="/resources/js/reply.js"></script>

	<script>
		$(document).ready(function() {

			var boardId = '<c:out value="${board.id}"/>';
			var replyUL = $(".chat"); //class 걸어놓는것
			
			showList(1);

			function showList(page) {

			console.log("show list " + page);

			replyService.getList(
				{
					board_id : boardId
				},
				function(list) {

					var str = "";
					
					if (list == null || list.length == 0) {
						replyUL.html("");
						return;
					}
					
					for (var i = 0, len = list.length || 0; i < len; i++) {
						str += "<li style='border-top-color: silver; border-top: 2px solid;'>"
					    str += "<div><strong>"+ list[i].reply_writer + "</strong><small style='float: right'>"
						str += "작성일:     " + list[i].reply_datetime + "</small></div><p>" + list[i].reply_content +"</p></li>";
					}

					replyUL.html(str);

				 });//end function
			}//end showList
		});
	</script>

	<!-- ajax 테스트 -->
	<script type="text/javascript">
		// 	console.log("===========");
		// 	console.log("JS TEST");

		// 	var boardId = '<c:out value="${board.id}"/>';

		// 	//add
		// // 	replyService.add(
		// // 			{
		// // 				board_id:boardId, 
		// // 				reply_content:"JS Test5", 
		// // 				reply_writer:"tester5"
		// // 			},

		// // 			function(result){
		// // 				alert("RESULT: " + result);	
		// // 			}
		// // 	);

		// 	//list
		// // 	replyService.getList(
		// // 		{board_id:boardId}, function(list){
		// // 			//for(var i=0, len=list.length||0; i<len; i++)
		// // 			for(var i=0, len=list.length; i<len; i++){
		// // 				console.log(list[i]);
		// // 			}
		// // 		}	
		// // 	);

		// 	//update
		// // 	replyService.update(
		// // 		{reply_id: "5", reply_content : "modify reply ...."},
		// // 		function(result) {
		// // 			alert("modify success...");
		// // 		}
		// // 	);

		// // 	delete
		// // 	replyService.remove(9, function(count) {

		// // 		console.log(count);

		// // 		if(count==="success"){
		// // 			alert("REMOVED");
		// // 		}
		// // 	}, function(err){
		// // 		alert('ERROR...');
		// // 	});
	</script>

</body>
</html>