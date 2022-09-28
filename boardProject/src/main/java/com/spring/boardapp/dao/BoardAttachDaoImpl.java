package com.spring.boardapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardapp.domain.BoardAttach;

@Repository("BoardAttachDao")
public class BoardAttachDaoImpl implements BoardAttachDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(BoardAttach boardAttach) {
		// TODO Auto-generated method stub
		sqlSession.insert("insert", boardAttach);
	}
	@Override
	public void delete(String uuid) {
		sqlSession.delete("delete", uuid);
		
	}
	@Override
	public List<BoardAttach> findBoardAttach(String board_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("findBoardAttach", board_id);
	}
	
	@Override
	public void deleteAllAttach(String board_id) {
		// TODO Auto-generated method stub
		sqlSession.delete("deleteAllAttach", board_id);
	}
}
