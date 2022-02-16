package com.ECA.Service;

public interface UserSendOtpService {
      StringBuffer sendVerificationCode(String user,Long mobileNumber) throws Exception;
}
