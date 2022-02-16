package com.ECA.Entity;


import com.ECA.Enums.Roles;
import com.ECA.Enums.Status;
import com.ECA.Enums.UserType;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ECA_USER_DETAILS")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_Id;
    private String userName;
    private Long mobileNumber;
    private String password;
    private LocalDate createdOn;
    private LocalDate modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private String email;
    private Status status;
    private Long otp;
    private String city;
    private String state;
    private UserType userType;
    private Roles roles;
}
