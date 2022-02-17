package com.ECA.Service;

import com.ECA.DTO.UserDetailsDTO;
import com.ECA.Entity.UserDetails;
import com.ECA.Enums.Roles;
import com.ECA.Enums.Status;
import com.ECA.Enums.UserType;
import com.ECA.Repository.UserDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class RegistrationServiceImp implements  RegistrationService{

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public String registerDetails(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails;
        if(userDetailsDTO.getUser_Id() !=null) {
            userDetails = userDetailsRepository.findAllByMobileNumber(userDetailsDTO.getMobileNumber());
        }else {
            userDetails =  new UserDetails();
        }
        BeanUtils.copyProperties(userDetailsDTO,userDetails);
        userDetails.setCreatedBy(userDetailsDTO.getUserName());
        userDetails.setCreatedOn(LocalDate.now());
        userDetails.setModifiedBy(userDetailsDTO.getUserName());
        userDetails.setModifiedOn(LocalDate.now());
        userDetails.setStatus(Status.Active);
        userDetails.setUserType(UserType.User);
        userDetails.setRoles(Roles.User);
        userDetailsRepository.save(userDetails);
        return "Success";
    }
}
