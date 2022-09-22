package com.spring.boardapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boardapp.domain.Reply;
import com.spring.boardapp.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Resource(name = "ReplyService")
	private ReplyService replyService;
	
	@PostMapping(value = "/new",
				consumes = "application/json",            //클라가 서버에 보내는 데이터 형식
				produces = {MediaType.TEXT_PLAIN_VALUE})  //서버가 클라에 보내는 데이터 형식
	public ResponseEntity<String> create(@RequestBody Reply reply){  //ResponseEntity: 응답 형태 보낼 수 있음
																	 //@RequestBody: 요청을 해당 타입으로 변환 여기선 (JSON->Reply)
		
		int insertResult = replyService.insertReply(reply);
		System.out.println("등록호출됨");
		
		return insertResult==1 ? new ResponseEntity<String>("success",HttpStatus.OK) :
							new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{board_id}",
				produces = {MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<Reply>> getReplyList(@PathVariable String board_id){
		return new ResponseEntity<List<Reply>>(replyService.getReplyList(board_id), HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value="/{reply_id}",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody Reply reply, @PathVariable String reply_id){
		
		reply.setReply_id(reply_id); //요청 데이터 댓글 번호 처리
		System.out.println("수정 호출됨");
		
		return replyService.updateReply(reply) ? new ResponseEntity<String>("success",HttpStatus.OK) :
												new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{reply_id}",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@PathVariable String reply_id){
		
		return replyService.deleteReply(reply_id) ? new ResponseEntity<String>("success", HttpStatus.OK):
													new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
