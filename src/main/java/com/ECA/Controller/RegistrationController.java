package com.ECA.Controller;

import com.ECA.DTO.Response;
import com.ECA.DTO.UserDetailsDTO;
import com.ECA.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eca")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/saveDetails")
    public ResponseEntity<Response> registerDetails(@RequestBody UserDetailsDTO userDetailsDTO){
        return new ResponseEntity<>(new Response("SUCCESS",registrationService.registerDetails(userDetailsDTO), HttpStatus.OK),HttpStatus.OK);
    }
}
