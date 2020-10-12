package com.dhatuonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dhatuonline.dto.ResetPasswordDto;
import com.dhatuonline.service.ResetPasswordService;

@Controller
public class ResetPasswordController {

	@Autowired
	private ResetPasswordService resetPasswordService;


	@PostMapping("/reset-password")
	public String forgotPassword(@ModelAttribute("resetPasswordDto") ResetPasswordDto resetPasswordDto) {
		boolean isReset = resetPasswordService.validateSecurityCodeAndResetPassword(resetPasswordDto);
		if (isReset) {
			return "login";			
		} else {	
			return "redirect:/reset-password?failure";			
		}
	}
	
}
