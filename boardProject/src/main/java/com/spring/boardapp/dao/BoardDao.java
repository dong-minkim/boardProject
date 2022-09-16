package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.BoardReply;

public interface BoardDao {
	
	//게시글 등록
	int insertBoard(Map<String, Object> paramMap);
	
	//게시글 읽기
	Board getBoardDetail(Map<String, Object> paramMap);
	
	//게시글 수정
	int updateBoard(Map<String, Object> paramMap);
	
	//게시글 삭제
	int deleteBoard(Map<String, Object> paramMap);
	
	//게시글 List
	List<Board> getBoardList(Map<String, Object> paramMap);
	
	//조회수 증가
	int updateBoardViews(Map<String, Object> paramMap);
//	
//	//댓글 등록
//	int insertReply(Map<String, Object> paramMap);
//	
//	//댓글 List
//	List<BoardReply> getReplyList(Map<String, Object> paramMap);
//	
//	//댓글 수정
//	int updateReply(Map<String, Object> paramMap);
//	
//	//댓글 삭제
//	int deleteReply(Map<String, Object> paramMap);
	
}
