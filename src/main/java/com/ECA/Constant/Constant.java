package com.ECA.Constant;

import org.springframework.web.bind.annotation.RequestParam;

public class Constant {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final int NUMBER=999999;
    public static final String API_KEY="TU6eHOMrSz7Riln5PdypYVxbIsfZD9hoWwmaGJ4A3Bv0g8CK12suqbmP5M1aZxeIFRNYpJcw7ESh3UC8";
    public static final String API_URL="https://www.fast2sms.com/dev/bulkV2?authorization=";

    // Response
    public static final String SUCCESS="SUCCESS";
    public static final String REGISTER_SUCCESS="Registration Successfully";

    // Exception
    public static final String NO_RECORD_FOUND="No Record Found";
    public static final String PASSWORD_SUCCESS="Password Update Successfully";
    public static final String WRONG_OTP="Wrong OTP.";
    public static final String REGISTER_DETAILS="Please Register Your Details";
    public static final String  ENTER_OLD_PASSWORD="Please Enter Old Password";
    public static final String ENTER_NEW_PASSWORD="Please Enter New Password";
    public static final String ENTER_CONFIRM_PASSWORD="Please Enter Confirm Password";
    public static final String OLD_PASSWORD_AND_NEW_PASSWORD_NOT_MATCH="New Password And Confirm Password Not Match";
    public static final String WRONG_OLD_PASSWORD="Wrong Old Password";
    public static final String MOBILE_NUMBER_ALREADY_REGISTER="This Mobile Number Is Already Register";
    public static final String ENTER_YOUR_NAME="Please Enter Your Name";
    public static final String ENTER_MOBILE_NUMBER="Please Enter Mobile Number";

    //url
    public static final String SAVE_DETAILS="/saveDetails";
    public static final String GET_USER_DETAILS="/getUserDetails";
    public static final String USER_SEND_OTP="/userSendOtp";
    public static final String VERIFY_OTP="/verifyOtp";
    public static final String FORGET_PASSWORD="/forgetPassword";
    public static final String EDIT_PASSWORD="/editPassword";

    // Project Url
    public static final String ECA="/eca";

  //  @RequestParam Value
    public static final String MOBILE_NUMBER="mobileNumber";
    public static final String USER_NAME="userName";
    public static final String OTP="otp";
    public static final String NEW_PASSWORD="newPassword";
    public static final String CONFIRM_PASSWORD="confirmPassword";
    public static final String OLD_PASSWORD="oldPassword";

}
