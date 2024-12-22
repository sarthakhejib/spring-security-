package com.api.spring_security.repository;

import com.api.spring_security.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser, Integer> {

    public SystemUser findByUsername(String user);
}
