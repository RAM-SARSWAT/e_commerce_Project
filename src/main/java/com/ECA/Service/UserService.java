package com.ECA.Service;

public interface UserService {
      StringBuffer sendVerificationCode(String user,Long mobileNumber) throws Exception;
}
