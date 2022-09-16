package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.boardapp.dao.BoardDao;
import com.spring.boardapp.domain.Board;

@Service("boardSerivce")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name = "boardDao")
	private BoardDao boardDao;
	
	@Override
	public int insertBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		if(paramMap.get("title")==null || paramMap.get("writer")==null) {
			return 0;
		}
		else return 0;
	}
	
	@Override
	public Board getBoardDetail(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Board> getBoardList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
