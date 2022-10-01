package com.spring.boardapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardapp.domain.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Member get(String userid) {
		// TODO Auto-generated method stub
		System.out.println("Member Dao 입니다.");
		return sqlSession.selectOne("get", userid);
	}
}
