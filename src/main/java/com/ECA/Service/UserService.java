package com.ECA.Service;

import com.ECA.DTO.UserDetailsDTO;
import com.ECA.Exception.BadRequestException;
import org.springframework.http.HttpStatus;

public interface UserService {
     String registerDetails(UserDetailsDTO userDetailsDTO) throws BadRequestException;

     UserDetailsDTO getUserDetailsByMobileNumber(Long mobileNumber) throws BadRequestException;
}
