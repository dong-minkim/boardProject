package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.boardapp.dao.BoardDao;
import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.Reply;
import com.spring.boardapp.domain.paging.Paging;

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
		boardDao.updateBoardViews(id);
		return boardDao.getBoardDetail(id);
	}

	@Override
	public boolean updateBoard(Map<String, Object> paramMap) {
		int result = boardDao.updateBoard(paramMap);
		if(result == 1) return true;
		else return false;
	}

	@Override
	public boolean deleteBoard(String id) {
		if(boardDao.deleteBoard(id)==1) return true;
		else return false;
	}

	@Override
	public List<Board> getBoardList() {
		// TODO Auto-generated method stub
		return boardDao.getBoardList();
	}
	
	@Override
	public List<Board> getBoardListWithPaging(Map<String, Object> paramMap) {
		int startBoardNum = ((Integer)paramMap.get("pageNum")-1) * (Integer)paramMap.get("pageAmount");
		paramMap.put("startBoardNum", startBoardNum);
		return boardDao.getBoardListWithPaging(paramMap);
	}
	
	@Override
	public int getBoardTotalCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getBoardTotalCnt(paramMap);
	}
}
