package com.anime.tracker.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anime.tracker.dto.LoginDto;
import com.anime.tracker.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {
		// 01 AuthenticationManger is used to authenticate the user
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(),
				loginDto.getPassword()
		));

		/* 02 - SecurityContextHolder is used to allow the rest of the application to know
		 * that the user is authenticated and can use user data from Authentication object */
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// 03 - Generate token based on username and secret key
		String token = jwtTokenProvider.generateToken(authentication);

		// 04 Return token to controller
		return token;
	}
}