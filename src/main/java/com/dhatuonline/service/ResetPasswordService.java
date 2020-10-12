package com.dhatuonline.service;

import com.dhatuonline.dto.ResetPasswordDto;

public interface ResetPasswordService {

	boolean validateSecurityCodeAndResetPassword(ResetPasswordDto resetPasswordDto);


}
