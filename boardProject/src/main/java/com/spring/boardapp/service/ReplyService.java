package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Reply;

public interface ReplyService {
	// 댓글 달기
	int insertReply(Reply reply);
	
	// 댓글 하나
	Reply getReply(String reply_id);

	// 댓글 목록 출력
	List<Reply> getReplyList(String board_id);

	// 댓글 수정
	boolean updateReply(Reply reply);

	// 댓글 삭제
	boolean deleteReply(String reply_id);
}
