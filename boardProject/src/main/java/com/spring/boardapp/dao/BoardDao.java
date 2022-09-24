package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.Reply;
import com.spring.boardapp.domain.paging.Paging;

public interface BoardDao {

	// 게시글 삽입
	int insertBoard(Map<String, Object> paramMap);

	// 게시글 읽기
	Board getBoardDetail(String id);

	// 게시글 수정
	int updateBoard(Map<String, Object> paramMap);

	// 게시글 삭제
	int deleteBoard(String id);

	// 게시글 List
	List<Board> getBoardList();

	// 페이징 게시글 List
	List<Board> getBoardListWithPaging(Map<String, Object> paramMap);

	// 게시글 전체 수
	int getBoardTotalCnt(Map<String, Object> paramMap);

	// 조회수 증가
	int updateBoardViews(String id);
	
	// 댓글 수 증가
	public int updateReplyCnt(Map<String, Object> paramMap);

}
