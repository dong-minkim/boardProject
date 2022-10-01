package com.spring.boardapp.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boardapp.dao.MemberDao;
import com.spring.boardapp.domain.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberServiceTests {
		
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void testRead() {
		
		System.out.println("test start");
		
		Member member = memberDao.get("admin0");
		
		System.out.println(member);
		
		member.getAuthList().forEach(auth->System.out.println(auth.getUserid() + " " + auth.getAuth()));
	}
}
