package com.spring.boardapp.dao;

import java.util.List;

public interface BoardAttachDao {
	public void insert(BoardAttachDao boardAttach);
	
	public void delete(String uuid);
	
	public List<BoardAttachDao> findBoardAttach(String board_id);
}
