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

public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long user_Id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "MOBILE_NUMBER")
    private Long mobileNumber;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATED_ON")
    private LocalDate createdOn;
    @Column(name = "MODIFIED_ON")
    private LocalDate modifiedOn;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "STATUS")
    private Status status;
    @Column(name = "OTP")
    private Long otp;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    private Roles roles;
}
