package com.dhatuonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dhatuonline.dto.ResetPasswordDto;
import com.dhatuonline.dto.UserDto;
import com.dhatuonline.service.ForgotPasswordService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		model.addAttribute("user", new UserDto());
		return "forgotPassword";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@ModelAttribute("user") UserDto userDto, Model model) {
		boolean isEmailSent = forgotPasswordService.createSecurityCodeAndSendEmail(userDto);
		if (isEmailSent) {
			model.addAttribute("resetPasswordDto", new ResetPasswordDto());
			return "resetPassword";
		} else {
			return "redirect:/forgot-password?failure";
		}
	}
}
