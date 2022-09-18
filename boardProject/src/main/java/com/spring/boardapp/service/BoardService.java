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
	boolean updateBoard(Map<String, Object> paramMap);
	
	//�Խñ� ����
	boolean deleteBoard(String id);
	
	//�Խñ� List
	List<Board> getBoardList();
	
}
