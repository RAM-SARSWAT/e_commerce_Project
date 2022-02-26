package com.ECA.Controller;

import com.ECA.DTO.Response;
import com.ECA.DTO.UserDetailsDTO;
import com.ECA.Exception.BadRequestException;
import com.ECA.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ECA.Constant.Constant.*;

@RestController
@RequestMapping(value = ECA)
public class UserController {
    @Autowired
    UserService userService;

    // Register User Details
    @PostMapping(SAVE_DETAILS)
    public ResponseEntity<Response> registerDetails(@RequestBody UserDetailsDTO userDetailsDTO) {
        return new ResponseEntity<>(new Response(SUCCESS, userService.registerDetails(userDetailsDTO), HttpStatus.OK), HttpStatus.OK);
    }
    // Get User Details
    @GetMapping(GET_USER_DETAILS)
    public ResponseEntity<Response>getUserDetailsByMobileNumber(@RequestParam(value = MOBILE_NUMBER)Long mobileNumber) throws BadRequestException {
        return new ResponseEntity<>(new Response(SUCCESS,userService.getUserDetailsByMobileNumber(mobileNumber),HttpStatus.OK),HttpStatus.OK);
    }

}
