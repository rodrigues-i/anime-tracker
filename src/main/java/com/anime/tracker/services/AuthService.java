package com.anime.tracker.services;

import com.anime.tracker.dto.LoginDto;

public interface AuthService {
	String login(LoginDto loginDto);
}