package com.ECA.Controller;

import com.ECA.DTO.Response;
import com.ECA.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/eca")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/sendVerificationCode")
    public ResponseEntity<Response> SendVerificationCode(@RequestParam(value = "UserName") String userName, @RequestParam(value = "MobileNumber") Long mobileNumber) throws Exception {
        return new ResponseEntity<>(new Response("SUCCESS", userService.sendVerificationCode(userName, mobileNumber), HttpStatus.OK), HttpStatus.OK);
    }
}