package com.ECA.Controller;

import com.ECA.DTO.Response;
import com.ECA.Service.UserSendOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/eca")
public class UserSendOtpController {

    @Autowired
    UserSendOtpService userService;

    // send Verification code
    @GetMapping(value = "/userSendOtp")
    public ResponseEntity<Response> sendVerificationCode(@RequestParam(value = "UserName") String userName, @RequestParam(value = "MobileNumber") Long mobileNumber) throws Exception {
        return new ResponseEntity<>(new Response("SUCCESS", userService.sendVerificationCode(userName, mobileNumber), HttpStatus.OK), HttpStatus.OK);
    }
    // verify OTP

    @GetMapping(value = "/verifyOtp")
    public ResponseEntity<Response> checkVerificationCode(@RequestParam(value = "otp") Long otp, @RequestParam(value = "MobileNumber") Long mobileNumber) throws Exception {
        return new ResponseEntity<>(new Response("SUCCESS", userService.checkVerificationCode(otp, mobileNumber), HttpStatus.OK), HttpStatus.OK);
    }

}