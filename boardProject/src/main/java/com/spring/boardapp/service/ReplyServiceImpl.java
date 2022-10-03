package com.spring.boardapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boardapp.dao.BoardDao;
import com.spring.boardapp.dao.ReplyDao;
import com.spring.boardapp.domain.Reply;

@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService {

	@Resource(name = "replyDao")
	private ReplyDao replyDao;
	
	@Resource(name = "boardDao")
	private BoardDao boardDao;

	@Transactional
	@Override
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", reply.getBoard_id());
		param.put("amount", "1");
		boardDao.updateReplyCnt(param);
		
		return replyDao.insertReply(reply);
	}
	
	@Override
	public Reply getReply(String reply_id) {
		// TODO Auto-generated method stub
		return replyDao.getReply(reply_id);
	}

	@Override
	public List<Reply> getReplyList(String board_id) {
		// TODO Auto-generated method stub
		return replyDao.getReplyList(board_id);
	}

	@Override
	public boolean updateReply(Reply reply) {
		int result = replyDao.updateReply(reply);
		if (result ==1)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean deleteReply(String reply_id) {
		// TODO Auto-generated method stub
		
		Reply reply = replyDao.getReply(reply_id);
		
		if(replyDao.deleteReply(reply_id)>0) {
			System.out.println("여기 " + reply_id);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", reply.getBoard_id());
			param.put("amount", "-1");
			boardDao.updateReplyCnt(param);
			
			return true;
		}
		else return false;
	}
	
	
}
