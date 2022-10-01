package com.spring.boardapp.dao;

import com.spring.boardapp.domain.Member;

public interface MemberDao {
	public Member get(String userid);
}
