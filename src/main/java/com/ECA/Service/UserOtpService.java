package com.ECA.Service;

import com.ECA.Exception.BadRequestException;

import java.io.IOException;

public interface UserOtpService {
    StringBuffer sendVerificationCode(String userName, Long mobileNumber) throws Exception;

    String checkVerificationCode(Long otp, Long mobileNumber) throws BadRequestException;

    StringBuffer forgetPassword(Long mobileNumber) throws BadRequestException, IOException;

    String editPassword(Long mobileNumber, String oldPassword, String newPassword, String confirmPassword) throws BadRequestException;
}
