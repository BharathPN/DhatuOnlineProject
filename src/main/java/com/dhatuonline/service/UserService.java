package com.dhatuonline.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dhatuonline.dto.UserDto;
import com.dhatuonline.model.User;

public interface UserService extends UserDetailsService {

	public User save(UserDto user);
	
}
