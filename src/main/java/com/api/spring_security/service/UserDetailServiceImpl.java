package com.api.spring_security.service;

import com.api.spring_security.model.SystemUser;
import com.api.spring_security.model.UserPrincipal;
import com.api.spring_security.repository.SystemUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user= repo.findByUsername(username);
        return new UserPrincipal(user);
    }
}
