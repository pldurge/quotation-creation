package com.example.carquotesystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carquotesystem.dto.ResponseStatus;
import com.example.carquotesystem.dto.UserRegisterRequestDTO;
import com.example.carquotesystem.dto.UserRegisterResponseDTO;
import com.example.carquotesystem.dto.CustomerRegisterRequestDTO;
import com.example.carquotesystem.dto.CustomerRegisterResponseDTO;
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

	@PostMapping("/registercustomer")
	public ResponseEntity<CustomerRegisterResponseDTO> signUp(@RequestBody CustomerRegisterRequestDTO userRequest){
		CustomerRegisterResponseDTO dto = new CustomerRegisterResponseDTO();
		User user = userService.registerNewUser(userRequest.getUsername(), userRequest.getPassword(), "customer");
		dto.setId(user.getId());
		dto.setStatus(ResponseStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@PostMapping("/registeruser")
	public ResponseEntity<UserRegisterResponseDTO> signUp(@RequestBody UserRegisterRequestDTO userRequest){
		UserRegisterResponseDTO dto = new UserRegisterResponseDTO();
		User user = userService.registerNewUser(userRequest.getUsername(), userRequest.getPassword(), userRequest.getRole());
		dto.setId(user.getId());
		dto.setStatus(ResponseStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
}
