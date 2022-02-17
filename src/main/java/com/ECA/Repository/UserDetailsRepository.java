package com.ECA.Repository;

import com.ECA.Entity.UserDetails;
import com.ECA.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    UserDetails findAllByMobileNumber(Long mobileNumber);
}
