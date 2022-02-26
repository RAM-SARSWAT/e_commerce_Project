package com.ECA.Service;

import com.ECA.DTO.UserDetailsDTO;
import com.ECA.Entity.UserDetails;
import com.ECA.Enums.Roles;
import com.ECA.Enums.Status;
import com.ECA.Enums.UserType;
import com.ECA.Exception.BadRequestException;
import com.ECA.Repository.UserDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.ECA.Constant.Constant.*;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String registerDetails(UserDetailsDTO userDetailsDTO) throws BadRequestException {
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(userDetailsDTO.getMobileNumber());
        if(userDetails == null){
            throw new BadRequestException(NO_RECORD_FOUND,HttpStatus.BAD_REQUEST);
        }
        userDetails.setUserName(userDetailsDTO.getUserName());
        userDetails.setPassword(userDetailsDTO.getPassword());
        userDetails.setCity(userDetailsDTO.getCity());
        userDetails.setEmail(userDetailsDTO.getEmail());
        userDetails.setState(userDetailsDTO.getState());
        userDetails.setCreatedBy(userDetailsDTO.getUserName());
        userDetails.setCreatedOn(LocalDate.now());
        userDetails.setModifiedBy(userDetailsDTO.getUserName());
        userDetails.setModifiedOn(LocalDate.now());
        userDetails.setStatus(Status.Active);
        userDetails.setUserType(UserType.User);
        userDetails.setRoles(Roles.User);
        userDetailsRepository.save(userDetails);
        return REGISTER_SUCCESS;
    }

    @Override
    public UserDetailsDTO getUserDetailsByMobileNumber(Long mobileNumber) throws BadRequestException {
        UserDetails userDetails = userDetailsRepository.findAllByMobileNumber(mobileNumber);
        if (userDetails == null) {
            throw new BadRequestException(NO_RECORD_FOUND, HttpStatus.BAD_REQUEST);
        }
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(userDetails, userDetailsDTO);
        return userDetailsDTO;
    }
}
