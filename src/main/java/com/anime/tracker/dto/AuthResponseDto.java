package com.anime.tracker.dto;

import java.util.Objects;

public class AuthResponseDto {

	private String accessToken;

	public AuthResponseDto() {}

	public AuthResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthResponseDto other = (AuthResponseDto) obj;
		return Objects.equals(accessToken, other.accessToken);
	}

	@Override
	public String toString() {
		return "AuthResponseDto [accessToken=" + accessToken + "]";
	}
}
