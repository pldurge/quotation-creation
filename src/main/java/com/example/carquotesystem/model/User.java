package com.example.carquotesystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseModel{

	@Column(unique = true)
	private String username;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

}
