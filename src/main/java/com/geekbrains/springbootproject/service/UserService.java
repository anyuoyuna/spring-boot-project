package com.geekbrains.springbootproject.service;


import com.geekbrains.springbootproject.entity.SystemUser;
import com.geekbrains.springbootproject.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {
    User findByUserName(Principal principal);
    boolean checkUserExisting(SystemUser theSystemUser);
}
