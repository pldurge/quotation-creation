package com.example.carquotesystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carquotesystem.dto.ResponseStatus;
import com.example.carquotesystem.dto.UserRequestDTO;
import com.example.carquotesystem.dto.UserResponseDTO;
import com.example.carquotesystem.model.User;
import com.example.carquotesystem.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO userRequest){
		UserResponseDTO dto = new UserResponseDTO();
		User user = userService.registerNewUser(userRequest.getUsername(), userRequest.getPassword(), userRequest.getRole());
		dto.setId(user.getId());
		dto.setStatus(ResponseStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
}
