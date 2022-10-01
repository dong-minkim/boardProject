package com.spring.boardapp;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

//@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//					   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class Test {
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	public void testInsertMember() {
		String sql = "INSERT INTO member(userid, userpw, username)"
				   + " VALUES(?,?,?)";
		
		for(int i=0;i<100;i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(2, pwEncoder.encode("pw" + i));
				
				if(i<2) {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
				}else if(i<10){
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				}else {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "일반사용자"+i);
				}
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) { try { pstmt.close();  } catch(Exception e) {} }
		        if(con != null) { try { con.close();  } catch(Exception e) {} }
			}
		}
	}
	
}
