package com.spring.boardapp.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.spring.boardapp.domain.Member;
import com.spring.boardapp.security.domain.CustomUser;
import com.spring.boardapp.dao.MemberDao;
//import com.spring.boardapp.domain.CustomUser;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDao memberDao;

	@Override //리턴 타입 UserDetails
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		System.out.println("Load User By UserName : " + userName);

		// userName means userid
		Member member = memberDao.get(userName);

		System.out.println("queried by member mapper: " + member);

		return member == null ? null : new CustomUser(member);
	} 

}
