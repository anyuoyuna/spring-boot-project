package com.geekbrains.springbootproject.config.security;

import com.geekbrains.springbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		if(!request.getHeader("referer").contains("login")) {
			response.sendRedirect(request.getHeader("referer"));
		} else {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}
