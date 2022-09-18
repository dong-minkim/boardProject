package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardapp.domain.Board;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public int insertBoard(Map<String, Object> paramMap) {
		return sqlSession.insert("insertBoard", paramMap);
	}
	
	@Override
	public Board getBoardDetail(String id) {
		return sqlSession.selectOne("selectBoard", id);
	}
	
	@Override
	public int updateBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.update("updateBoard", paramMap);
	}
	
	@Override
	public int deleteBoard(String id) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteBoard", id);
	}
	
	@Override
	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectBoardList");
	}
	
	@Override
	public int updateBoardViews(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
