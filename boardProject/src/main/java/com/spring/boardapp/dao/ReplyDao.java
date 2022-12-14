package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Reply;

public interface ReplyDao {
	// 댓글 달기
	int insertReply(Reply reply);
	
	// 댓글 조회
	Reply getReply(String reply_id);

	// 댓글 목록 출력
	List<Reply> getReplyList(String board_id);

	// 댓글 수정
	int updateReply(Reply reply);

	// 댓글 삭제
	int deleteReply(String reply_id);
	
	// 게시물 댓글들 삭제
	int deleteAllReply(String board_id);
}
