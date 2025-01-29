package com.anime.tracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.dto.AuthResponseDto;
import com.anime.tracker.dto.LoginDto;
import com.anime.tracker.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
		
		String token = authService.login(loginDto);

		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setAccessToken(token);

		return new ResponseEntity<>(authResponseDto, HttpStatus.CREATED);
	}
}