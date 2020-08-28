package com.example.demo.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	public int userId;
	public String userName;
	public Optional<String> password;
	public LocalDateTime loginDate;
	public boolean isOAuthUser;

	public String getPassword() {
			if(password.isPresent()) {
					return password.get();
			}
			return null;
	}

}
