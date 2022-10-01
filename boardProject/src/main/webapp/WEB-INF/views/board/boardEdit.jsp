<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<title>게시글 수정</title>
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
</head>
<body>
	<h2>게시글 수정</h2>
	<form action="/board/edit/${board.id}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
		<input type="hidden" name="pageNum" value="${pageNum }"> 
		<input type="hidden" name="pageAmount" value="${pageAmount}">
		<input type="hidden" name="id" id="id" value="${board.id }" />
		<table border="1" width="930px">
			<caption>
				<strong>* 표시: 수정 가능</strong>
			</caption>

			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup>
			<tr>
				<th>제목 *</th>
				<td><input type="text" style="width: 300px; border: none"
					name="title" id="title" value="${board.title }" required />
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" style="border:none" name="writer" value="${board.writer }" readonly="readonly"/>
			</tr>
			<tr height="300px">
				<th>내용*</th>
				<td><textarea style="border: none;" rows="40" cols="120"
						name="content">${board.content }</textarea>
			</tr>
		</table>
		<table width="930px">
			<tr>
				<td colspan="2" align="right">
					<div>
						<input type="button" value="목록" onclick="location.href='/board/list?pageNum=${pageNum }&pageAmount=${pageAmount}'">
						<input type="reset" value="초기화" /> 
						<button type="submit">수정</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<br />
	<!-- Attachment -->
		<div class="bigPictureWrapper">
			<div class="bigPicture">
			</div>
		</div>
		<div style="border-radius: 7px; border: 2px solid; border-color: silver; width: 930px; margin: auto;  ">
			<div style="text-align: center;"><strong> Attachment </strong></div><br>
			<div>
				<div>
					<input type="file" name="uploadFile" multiple>
				</div>
				<div class="uploadResult">
					<ul>
					</ul>
				</div>
			</div>
		</div>
	
	<script>
	$(document).ready(function(e){
			
			var formObj = $("form");
			
			$("button[type='submit']").on("click", function(e){
				
				e.preventDefault();
				
				console.log("submit clicked");
				
				var str = "";
				
				$(".uploadResult ul li").each(function(i, obj){
				      
					var jobj = $(obj);
				      
				    console.dir(jobj);
				    console.log("-------------------------");
				    console.log(jobj.data("filename"));
				      
				    //게시글 등록은 <form> 태그를 통해서 이루어진다.
				    //이미 업로드된 정보는 별도의 <input type='hidden'> 태그를 생성해서 처리한다.
				    //Board에서 이에 대한 List 이름이 attachList이므로 name을 맞춰준다.
				    str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
				    str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				    str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
				    str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
				      
				});
				
				console.log(str);
				
				formObj.append(str).submit();
			});
			
			
			
			// 확장자, 크기 제한
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 10485760; //10MB
			  
			function checkExtension(fileName, fileSize){
				if(fileSize > maxSize){
					alert("파일 사이즈 초과");
					return false;
				}
			    
				if(regex.test(fileName)){
					alert("해당 종류의 확장자는 업로드할 수 없습니다.");
			      	return false;
			    }
			    return true;
			}
			
						
			//jquery change() 메소드
			$("input[type='file']").change(function(e){

				//파일 업로드는 FormData 객체를 이용한다.
				//FormData는 쉽게 말해서 가상의 <form> 태그와 같다.
				//Ajax 이용하여 파일 업로드 할 때 필요한 파라미터를 여기서 담아 전송한다.
			    var formData = new FormData();
			    
			    var inputFile = $("input[name='uploadFile']");
			    
			    var files = inputFile[0].files;
			    
			    for(var i = 0; i < files.length; i++){
			    	if(!checkExtension(files[i].name, files[i].size) ){
			        	return false;
			      	}
			      	formData.append("uploadFile", files[i]);
			    }
			    
			    $.ajax({
			    	url: '/uploadAjaxAction',
			      	processData: false, 
			      	contentType: false,
			      	data:formData,
			      	type: 'POST',
			      	dataType:'json',
			        success: function(result){
			        	console.log(result); 
					  	showUploadResult(result); //업로드 결과 처리 함수 

			      	}
				}); //$.ajax
			});
			
			// 업로드된 파일 보여주기
			function showUploadResult(uploadResultArr){
				if(!uploadResultArr || uploadResultArr.length == 0){ return; }
				
				var uploadUL = $(".uploadResult ul");
				
				var str = "";
				
				$(uploadResultArr).each(function(i, obj){
					if(obj.image){ //이미지 파일인 경우
						
						var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
						str += "<li data-path='"+obj.uploadPath+"'";
						str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>"
						str += "<div><span> "+ obj.fileName+"</span>";
						str += "<button type='button' style='border-radius:20px' data-file=\'"+fileCallPath+"\' "
						str += "data-type='image'>x<i class='fa fa-times'></i></button><br>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div></li>";
					}else{
						var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
					    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
					      
						str += "<li "
						str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' >";
						str += "<div><span> "+ obj.fileName+"</span>";
						str += "<button type='button' style='border-radius:20px' data-file=\'"+fileCallPath+"\' data-type='file' " 
						str += ">x<i class='fa fa-times'></i></button><br>";
						str += "<img src='/resources/img/attach.png'></a>";
						str += "</div></li>";
					}
				});
				
				uploadUL.append(str);
			}
		
		  	$(".uploadResult").on("click", "button", function(e){
			    
				console.log("delete file");
			      
			    var targetFile = $(this).data("file");
			    var type = $(this).data("type");
			    var targetLi = $(this).closest("li");
			    
			    $.ajax({
			    	url: '/deleteFile',
			      	data: {fileName: targetFile, type:type},
			      	dataType:'text',
			      	type: 'POST',
			        success: function(result){
			        	alert(result);
			        	targetLi.remove();
			        }
			    }); //$.ajax
			});
		

	});
	</script>
	
	<!-- 기존 게시물 목록 보여주기 -->
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
							str += "<div><span> "+ attach.fileName+"</span>"
							str += "<button type='button' style='border-radius:20px' data-file=\'"+fileCallPath+"\' data-type='image' >x</button>"
							str += "<br/><br/><img src='/display?fileName="+fileCallPath+"'>";
							str += "</div></li>";
						}else{ 
						 	str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' >";
							str += "<div><span> "+ attach.fileName+"</span>"
							str += "<button type='button' style='border-radius:20px' data-file=\'"+fileCallPath+"\' data-type='file' >x</button><br/><br/>";
							str += "<img src='/resources/img/attach.png'></a>";
							str += "</div></li>";
						}
					});
					
					$(".uploadResult ul").html(str);		
				}
			);
			
		})();
		
	});
	</script>
</body>
</html>