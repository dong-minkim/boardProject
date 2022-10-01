package com.spring.boardapp.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring.boardapp.domain.Member;

public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(Member member) {

		super(member.getUserid(), 
			  member.getUserpw(), 
			  member.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())) //Auto를 GrantedAuthority 객체로 변환
										   .collect(Collectors.toList()));

		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
