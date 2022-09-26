package com.spring.boardapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardAttachDaoImpl implements BoardAttachDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(BoardAttachDao boardAttach) {
		// TODO Auto-generated method stub
		sqlSession.insert("insert", boardAttach);
	}
	@Override
	public void delete(String uuid) {
		sqlSession.delete("uuid", uuid);
		
	}
	@Override
	public List<BoardAttachDao> findBoardAttach(String board_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("findBoardAttach", board_id);
	}
}
