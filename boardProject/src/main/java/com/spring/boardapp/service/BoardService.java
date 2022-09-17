package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Board;

public interface BoardService {
	
	//�Խñ� ���
	int insertBoard(Map<String, Object> paramMap);
	
	//�Խñ� �б�
	Board getBoardDetail(String id);
	
	//�Խñ� ����
	int updateBoard(Map<String, Object> paramMap);
	
	//�Խñ� ����
	int deleteBoard(Map<String, Object> paramMap);
	
	//�Խñ� List
	List<Board> getBoardList();
	
}
