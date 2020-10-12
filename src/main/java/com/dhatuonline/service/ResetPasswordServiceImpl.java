package com.dhatuonline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dhatuonline.dto.ResetPasswordDto;
import com.dhatuonline.model.User;
import com.dhatuonline.repo.UserRepository;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean validateSecurityCodeAndResetPassword(ResetPasswordDto resetPasswordDto) {
		try {
			User user = userRepository.findByUserName(resetPasswordDto.getUserName());
			if (user != null && user.getSecurityCode().equals(resetPasswordDto.getSecurityCode())
					&& resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword())) {
				user.setPassword(resetPasswordDto.getConfirmPassword());
				userRepository.save(user);
				return true;
			} else {
				throw new UsernameNotFoundException("Unable to find User");
			}

		} catch (Exception e) {
			return false;
		}
	}

}
