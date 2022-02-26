package com.ECA.DTO;

import com.ECA.Enums.Roles;
import com.ECA.Enums.UserType;
import lombok.Data;

@Data
public class UserDetailsDTO {
    private Long user_Id;
    private String userName;
    private Long mobileNumber;
    private String password;
    private String email;
    private String city;
    private String state;
    private UserType userType;
    private Roles roles;
}
