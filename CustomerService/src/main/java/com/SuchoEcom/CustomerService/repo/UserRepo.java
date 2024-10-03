package com.SuchoEcom.CustomerService.repo;

import com.SuchoEcom.CustomerService.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,String> {

    Users findAllByphoneNo(String ph);

    Users findAllByusername(String username);
}
