package com.spring.boardapp.dao;

import java.util.List;

import com.spring.boardapp.domain.BoardAttach;

public interface BoardAttachDao {
	public void insert(BoardAttach boardAttach);
	
	public void delete(String uuid);
	
	public List<BoardAttachDao> findBoardAttach(String board_id);
}
