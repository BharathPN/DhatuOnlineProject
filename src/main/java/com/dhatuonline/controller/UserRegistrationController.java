package com.dhatuonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhatuonline.dto.UserDto;
import com.dhatuonline.model.User;
import com.dhatuonline.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserDto());
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
		User user = userService.save(registrationDto);
		if (user == null) {
			return "redirect:/registration?failure";
		} else {
			return "redirect:/registration?success";
		}
	}
}
