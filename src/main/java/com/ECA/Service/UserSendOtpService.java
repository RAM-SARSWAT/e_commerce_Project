package com.ECA.Service;

import com.ECA.Exception.BadRequestException;

public interface UserSendOtpService {
    StringBuffer sendVerificationCode(String userName, Long mobileNumber) throws Exception;

    String checkVerificationCode(Long otp, Long mobileNumber) throws BadRequestException;
}
