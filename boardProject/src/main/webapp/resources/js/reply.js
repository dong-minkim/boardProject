/**
 * 
 */
 console.log("Reply Module....");
 
 var replyService=(function(){
 	
 	function add(reply, callback, error){
 		console.log("Add Reply.........");
 		
 		$.ajax({
 			type : 'post',
 			url : '/reply/new',
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				}
 			},
 			error : function(xhr, status, er){
 				if(error){
 					error(er);
 				}
 			}
 		})
 	}
 	
 	
 	function getList(param, callback, error){
 		var boardId = param.board_id;
 		
 		$.getJSON("/reply/" + boardId + ".json",
 			function(data){
 				if(callback){
 					callback(data);
 				}
 			}).fail(function(xhr, status, err){
 			if(error){
 				error();
 			}
 		});
 	}
 	
 	function update(reply, callback, error){
 	
 		console.log('reply_id: ' + reply.reply_id);
 		
 		$.ajax({
 			type : 'put',
 			url : '/reply/' + reply.reply_id,
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				}
 			},
 			error : function(xhr, status, er){
 				if(error){
 					error(er);
 				}
 			}
 		});
 	}
 	
 	function remove(reply_id, callback, error){
 		$.ajax({
 			type : 'delete',
 			url : '/reply/' + reply_id,
 			success : function(deleteResult, status, xhr){
 				if(callback){
 					callback(deleteResult);
 				}
 			},
 			error : function(xhr, status, er){
 				if(error){
 					error(er);
 				}
 			}
 		});
 	}
 	
 	
 	
 	return {
 		add : add,
 		getList : getList,
 		update : update,
 		remove : remove
 	};
 	
 })();