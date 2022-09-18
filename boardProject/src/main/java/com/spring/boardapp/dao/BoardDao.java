package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.BoardReply;

public interface BoardDao {
	
	//게시글 삽입
	int insertBoard(Map<String, Object> paramMap);
	
	//게시글 읽기
	Board getBoardDetail(String id);
	
	//게시글 수정
	int updateBoard(Map<String, Object> paramMap);
	
	//게시글 삭제
	int deleteBoard(String id);
	
	//게시글 List
	List<Board> getBoardList();
	
	//조회수 증가
	int updateBoardViews(String id);
//	
//	//��� ���
//	int insertReply(Map<String, Object> paramMap);
//	
//	//��� List
//	List<BoardReply> getReplyList(Map<String, Object> paramMap);
//	
//	//��� ����
//	int updateReply(Map<String, Object> paramMap);
//	
//	//��� ����
//	int deleteReply(Map<String, Object> paramMap);
	
}
