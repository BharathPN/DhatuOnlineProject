package com.dhatuonline.service;

import com.dhatuonline.dto.UserDto;

public interface ForgotPasswordService {

	boolean createSecurityCodeAndSendEmail(UserDto userDto);

}
