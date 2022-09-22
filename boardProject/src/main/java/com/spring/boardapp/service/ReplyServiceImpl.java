package com.spring.boardapp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.boardapp.dao.ReplyDao;
import com.spring.boardapp.domain.Reply;

@Service("ReplyService")
public class ReplyServiceImpl implements ReplyService {

	@Resource(name = "ReplyDao")
	private ReplyDao replyDao;

	@Override
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		return replyDao.insertReply(reply);
	}

	@Override
	public List<Reply> getReplyList(String board_id) {
		// TODO Auto-generated method stub
		return replyDao.getReplyList(board_id);
	}

	@Override
	public boolean updateReply(Reply reply) {
		int result = replyDao.insertReply(reply);
		if (result ==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteReply(String reply_id) {
		// TODO Auto-generated method stub
		if (replyDao.deleteReply(reply_id) == 1)
			return true;
		else
			return false;
	}
}
