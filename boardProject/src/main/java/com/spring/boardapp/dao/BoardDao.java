package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.BoardReply;

public interface BoardDao {
	
	//�Խñ� ���
	int insertBoard(Map<String, Object> paramMap);
	
	//�Խñ� �б�
	Board getBoardDetail(Map<String, Object> paramMap);
	
	//�Խñ� ����
	int updateBoard(Map<String, Object> paramMap);
	
	//�Խñ� ����
	int deleteBoard(Map<String, Object> paramMap);
	
	//�Խñ� List
	List<Board> getBoardList(Map<String, Object> paramMap);
	
	//��ȸ�� ����
	int updateBoardViews(Map<String, Object> paramMap);
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
