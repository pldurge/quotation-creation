package com.example.carquotesystem.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
	private String username;
	private String password;
	private String role;
}
