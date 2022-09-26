package com.spring.boardapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardapp.domain.Reply;

@Repository("ReplyDao")
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	SqlSession sqlSession;
	
	// 댓글
	@Override
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertReply", reply);
	}
	
	@Override
	public Reply getReply(String reply_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectReply", reply_id);
	}

	@Override
	public List<Reply> getReplyList(String board_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectReplyList", board_id);
	}

	@Override
	public int updateReply(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.update("updateReply", reply);
	}

	@Override
	public int deleteReply(String reply_id) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteReply", reply_id);
	}
}
