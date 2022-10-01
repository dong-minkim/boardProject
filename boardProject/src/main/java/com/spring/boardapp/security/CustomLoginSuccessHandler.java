package com.spring.boardapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
										Authentication auth) throws IOException, ServletException {

		System.out.println("Login Success");

		List<String> roleNames = new ArrayList<>();

		//사용자가 가진 모든 권한 문자열로 체크
		auth.getAuthorities().forEach(authority -> {

			roleNames.add(authority.getAuthority());

		});

		System.out.println("ROLE NAMES: " + roleNames);

		//관리자 권한 갖고 있으면 ADMIN으로 이동
		if (roleNames.contains("ROLE_ADMIN")) {

			response.sendRedirect("/sample/admin");
			return;
		}

		if (roleNames.contains("ROLE_MEMBER")) {

			response.sendRedirect("/sample/member");
			return;
		}

		response.sendRedirect("/");
	}
}


