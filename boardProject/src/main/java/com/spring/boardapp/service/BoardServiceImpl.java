package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boardapp.dao.BoardAttachDao;
import com.spring.boardapp.dao.BoardDao;
import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.BoardAttach;
import com.spring.boardapp.domain.Reply;
import com.spring.boardapp.domain.paging.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDao")
	private BoardDao boardDao;
	
	@Resource(name = "BoardAttachDao")
	private BoardAttachDao boardAttachDao;

	@Override
	public int insertBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		if (paramMap.get("title") == null || paramMap.get("writer") == null) {
			return 0;
		} else {
			int attachSize = (paramMap.size()-3)/4;
			
			int result = boardDao.insertBoard(paramMap);
			String id = String.valueOf(paramMap.get("id"));
			
			for(int i=0;i<attachSize;i++) {
				String tmp = "attachList["+i+"].";
				String uuid = String.valueOf(paramMap.get(tmp+"uuid"));
				String uploadPath = String.valueOf(paramMap.get(tmp+"uploadPath"));
				String fileName = String.valueOf(paramMap.get(tmp+"fileName"));
				String fileType = String.valueOf(paramMap.get(tmp+"fileType"));
				
				BoardAttach attach = new BoardAttach(uuid,uploadPath,fileName,Boolean.parseBoolean(fileType),id);
				boardAttachDao.insert(attach);
			}
			
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
	
	@Transactional
	@Override
	public boolean deleteBoard(String id) {
		boardAttachDao.deleteAllAttach(id);
		return boardDao.deleteBoard(id) == 1;
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
	
	@Override
	public List<BoardAttach> getAttachList(String id) {
		// TODO Auto-generated method stub
		return boardAttachDao.findBoardAttach(id);
	}
}
