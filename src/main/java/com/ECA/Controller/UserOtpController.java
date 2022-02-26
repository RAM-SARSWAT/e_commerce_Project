package com.ECA.Controller;

import com.ECA.DTO.Response;
import com.ECA.Exception.BadRequestException;
import com.ECA.Service.UserOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.ECA.Constant.Constant.*;


@RestController
@RequestMapping(value = ECA)
public class UserOtpController {

    @Autowired
    UserOtpService userOtpService;

    // send Verification code
    @GetMapping(value = USER_SEND_OTP)
    public ResponseEntity<Response> sendVerificationCode(@RequestParam(value = USER_NAME) String userName, @RequestParam(value =MOBILE_NUMBER) Long mobileNumber) throws Exception {
        return new ResponseEntity<>(new Response(SUCCESS, userOtpService.sendVerificationCode(userName, mobileNumber), HttpStatus.OK), HttpStatus.OK);
    }
    // verify OTP

    @GetMapping(value = VERIFY_OTP)
    public ResponseEntity<Response> checkVerificationCode(@RequestParam(value =OTP) Long otp, @RequestParam(value = MOBILE_NUMBER) Long mobileNumber) throws Exception {
        return new ResponseEntity<>(new Response(SUCCESS, userOtpService.checkVerificationCode(otp, mobileNumber), HttpStatus.OK), HttpStatus.OK);
    }

    // verify during forget Password

    @GetMapping(value = FORGET_PASSWORD)
    public ResponseEntity<Response>forgetPassword(@RequestParam(value =MOBILE_NUMBER) Long mobileNumber) throws BadRequestException, IOException {
        return new ResponseEntity<>(new Response(SUCCESS, userOtpService.forgetPassword(mobileNumber),HttpStatus.OK),HttpStatus.OK);
    }

    // Edit Password
    @PostMapping(value = EDIT_PASSWORD)
    public ResponseEntity<Response>editPassword(@RequestParam(value =MOBILE_NUMBER)Long mobileNumber,@RequestParam(value = OLD_PASSWORD) String oldPassword,@RequestParam(value = NEW_PASSWORD)String newPassword,@RequestParam(value = CONFIRM_PASSWORD)String confirmPassword) throws BadRequestException {
        return new ResponseEntity<>(new Response(SUCCESS, userOtpService.editPassword(mobileNumber,oldPassword,newPassword,confirmPassword),HttpStatus.OK),HttpStatus.OK);
    }
    // forget Password
     @GetMapping(value = UPDATE_PASSWORD)
     public ResponseEntity<Response>updatePassword(@RequestParam(value =MOBILE_NUMBER)Long mobileNumber,@RequestParam(value = NEW_PASSWORD)String newPassword,@RequestParam(value = CONFIRM_PASSWORD) String confirmPassword) throws BadRequestException {
        return new ResponseEntity<>(new Response(SUCCESS,userOtpService.updatePassword(mobileNumber,newPassword,confirmPassword),HttpStatus.OK),HttpStatus.OK);
     }
}