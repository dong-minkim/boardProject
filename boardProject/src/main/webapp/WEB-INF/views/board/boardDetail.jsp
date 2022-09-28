<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<style>
h1, h2 {
	text-align: center;
}

table {
	margin: auto;
}
.uploadResult {
  width:100%;
  background-color: green;
}
.uploadResult ul{
  display:flex;
  flex-flow: row;
  background-color: green;
  justify-content: center;
  align-items: center;
}
.uploadResult ul li {
  list-style: none;
  border:3px solid white;
  border-radius:10px;
  padding: 10px;
  margin : 5px;
  align-content: center;
  text-align: center;
}
.uploadResult ul li img{
  width: 100px;
}
.uploadResult ul li span {
  border:3px solid white;
  border-radius:10px;
  padding: 10px;
  
  margin: 5px;
  font-weight: bold;
  color:white;
}
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
  background:rgba(255,255,255,0.5);
}
.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}

.bigPicture img {
  width:600px;
}

</style>
<title>게시글(${board.id })</title>
</head>
<body>
	<h1>게시글(${board.id })</h1>
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
	<!-- Attachment -->
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		</div>
	</div>
	<div style="border-radius: 7px; border: 2px solid; border-color: silver; width: 930px; margin: auto;  ">
		<div style="text-align: center;"><strong> Attachment </strong></div><br>
		<div>
			<div class="uploadResult">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	
	
	
	<br />
	<br />
	<!-- Reply -->
	<div style="width: 930px; margin: auto;">
		<br>
		
		<div style="border-radius: 7px; border: 2px solid; border-color: silver;  ">
			<div style="text-align: center;"><strong> Writer Reply </strong></div><br>
			<div>
				<label style="padding:15px">작성자</label><br>
				<input type="text" style="border-color: silver; border-radius: 15px;width:95%; height:40px; margin:10px; padding:10px" id="reply_writer" name="reply_writer" value="">
			</div>
			<br>
			<br>
			<div>
				<label style="padding:15px">내용</label><br>
				<textarea rows="" cols="" style="border-color: silver; border-radius: 15px; width:95%; height:80px; margin:10px; padding:10px" id="reply_content" name="reply_content" value=""></textarea>
<!-- 				<input type="text"style="border-color: silver; border-radius: 15px; width:98%; height:80px; margin:10px;" id="reply_content" name="reply_content" value="">	 -->
			</div>
				<br>
				<br>
			<div style="float:right; padding:10px;">
			<button id="registReplyBtn" style ="height:25px; border-radius: 20px;"">
				Register
			</button>
			</div>
				<br>
				<br>
		</div>
		<br>
		<br>
		<div>
			<ul class="chat" style="padding-left: 0px;">
			</ul>
		</div>
	</div>

	<button data-delete="hi"></button>

	<!-- 댓글 -->
	<script type="text/javascript" src="/resources/js/reply.js"></script>
	<script>
		$(document).ready(function() {

			var boardId = '<c:out value="${board.id}"/>';
			var replyUL = $(".chat"); //class 걸어놓는것
			
			showReplyList();

			function showReplyList() {

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
						str += "<li style='list-style:none; border-color: red; border: 1px solid;border-radius: 4px;'>";
					    str += "<div style='padding:10px'><strong>"+ list[i].reply_writer + "</strong><small style='float: right'>";
						str += list[i].reply_datetime + "</small></div><p style='padding:10px'>" + list[i].reply_content +"</p>";
						str += "<div><input type=button class='replyDeleteBtn'  data-delete='"+ list[i].reply_id + "' value='삭제'style='float:right; width:60px; height:30px; border-radius: 20px;'><br><br></div></li><br>";
					}
					

					replyUL.html(str);
					$("input:button.replyDeleteBtn").on('click',replyDeleteBtn)

				 });//end function
			}//end showList
			
			$('#registReplyBtn').click(function(){
				var registReplyWriter = document.getElementById('reply_writer').value.trim();
				var registReplyContent = document.getElementById('reply_content').value.trim();
				
				if(registReplyWriter == "" || registReplyContent == ""){
					alert("항목을 채워주세요");
					
				}
				else{
					var reply = {
							board_id : boardId,
							reply_writer : registReplyWriter,
							reply_content : registReplyContent
					};
					
					replyService.add(
							reply,
							function(result){
								alert(result);
								showReplyList();
								$('input[name=reply_writer]').attr('value',"");
							}
					);
					
				}
			})
			
			
			function replyDeleteBtn(){
				
				var replyId = $(this).attr("data-delete");
				
				replyService.remove(
					replyId, 
					function(result) {
						if(result==="success"){
							alert("REMOVED");
							showReplyList();
						}
					}, 
					function(err){
					alert('ERROR...');
				});
			}
			
		});
		
	</script>

	<!-- ajax 테스트 -->
	<script>
		// 	console.log("===========");
		// 	console.log("JS TEST");

// 		 	var boardId = '<c:out value="${board.id}"/>';

// // 			add
// 			replyService.add(
// 					{
// 						board_id:boardId, 
// 						reply_content:"JS Test6", 
// 						reply_writer:"tester6"
// 					},

// 					function(result){
// 						alert("RESULT: " + result);	
// 					}
// 			);

		// 	//list
		// // 	replyService.getList(
		// // 		{board_id:boardId}, function(list){
		// 			//for(var i=0, len=list.length||0; i<len; i++)
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
	
	<script>
	$(document).ready(function(){
		
		//즉시 실행 함수
		(function(){
			
			var boardId = '<c:out value="${board.id}"/>';
			
			$.getJSON("/board/getAttachList",
				{id:boardId},
				function(arr){
					console.log(arr);
						  
					var str="";
					$(arr).each(function(i, attach){
						//image type
						if(attach.fileType=='1'){
							var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
						    console.log(fileCallPath);
							str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' >";
							str += "<div><span> "+ attach.fileName+"</span><br/><br/><img src='/display?fileName="+fileCallPath+"'>";
							str += "</div></li>";
						}else{ 
						 	str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' >";
							str += "<div><span> "+ attach.fileName+"</span><br/><br/>";
							str += "<img src='/resources/img/attach.png'></a>";
							str += "</div></li>";
						}
					});
					
					$(".uploadResult ul").html(str);		
				}
			);
			
		})();
		
		$(".uploadResult").on("click","li", function(e){
		      
			console.log("view image");
			    
			var liObj = $(this);
			    
			var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));
			    
			if(liObj.data("type")=="1"){
				showImage(path.replace(new RegExp(/\\/g),"/"));
			}else {
				//download 
				self.location ="/download?fileName="+path
			}
			    
			    
		});
			  
		function showImage(fileCallPath){
				    
			alert(fileCallPath);
			    
			$(".bigPictureWrapper").css("display","flex").show();
			    
			$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"' >")
			    			.animate({width:'100%', height: '100%'}, 0);
			    
		}

		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width:'0%', height: '0%'}, 0);
			setTimeout(function(){ $('.bigPictureWrapper').hide();}, 1000);
		});
		
	});
	</script>

</body>
</html>