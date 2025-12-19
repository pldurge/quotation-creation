package com.example.carquotesystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.carquotesystem.exception.UnableToRegisterUser;
import com.example.carquotesystem.model.Role;
import com.example.carquotesystem.model.User;
import com.example.carquotesystem.repository.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User registerNewUser(String userName, String password, String userType) throws UnableToRegisterUser{
		User user = new User();
		user.setUsername(userName);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setRole(Role.valueOf(userType.toUpperCase()));
		return userRepository.save(user);
	}
}
