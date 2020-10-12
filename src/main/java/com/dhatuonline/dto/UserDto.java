package com.dhatuonline.dto;

import java.util.List;

public class UserDto {

	private String userName;
	private String password;
	private String email;
	private List<String> roles;

	public UserDto(String userName, String password, String email, List<String> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public UserDto() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
