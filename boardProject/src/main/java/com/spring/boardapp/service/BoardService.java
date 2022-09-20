package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import com.spring.boardapp.domain.Board;

public interface BoardService {
	
	//게시글 등록
	int insertBoard(Map<String, Object> paramMap);
	
	//게시글 상세보기
	Board getBoardDetail(String id);
	
	//게시글 수정
	boolean updateBoard(Map<String, Object> paramMap);
	
	//게시글 삭제
	boolean deleteBoard(String id);
	
	//게시글 List
	List<Board> getBoardList();
	
	//게시글 페이징 List
	List<Board> getBoardListWithPaging(Map<String, Object> paramMap);

	//게시글 전체 개수
	int getBoardTotalCnt(Map<String, Object> paramMap);
}
