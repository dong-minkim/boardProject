package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.boardapp.dao.BoardDao;
import com.spring.boardapp.domain.Board;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDao")
	private BoardDao boardDao;

	@Override
	public int insertBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		if (paramMap.get("title") == null || paramMap.get("writer") == null) {
			return 0;
		} else {
			int result = boardDao.insertBoard(paramMap);
			return result;
		}
	}

	@Override
	public Board getBoardDetail(String id) {
		// TODO Auto-generated method stub
		return boardDao.getBoardDetail(id);
	}

	@Override
	public int updateBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		return boardDao.getBoardList();
	}
}
