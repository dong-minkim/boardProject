<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>

<style>
h2 {
	text-align: center;
}

table {
	margin: auto;
}

.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<title>게시글 작성</title>
</head>
<body>
	<h2>게시글 작성</h2>
	<form action="/board/regist", method = "post">
	<table border="1" width="930px">
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
	<table width="930px">
		<tr>
			<td align="right"><input type="button" value="목록" onclick="location.href='/board/list'">
		<button type="reset">초기화</button>
		<button type="submit">등록</button></td>
		</tr>
	</table>
	</form>
	<br />
	<div style="border-radius: 7px; border: 2px solid; border-color: silver; width: 930px; margin: auto;  ">
    <div style="text-align: center;"><strong> Attachment </strong></div><br>
    	<div>
        	<div class="uploadDiv">
            	<input type="file" name='uploadFile' multiple>
        	</div>
        
        	<div class='uploadResult'> 
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
						str += ">x</button><br>";
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
</body>
</html>