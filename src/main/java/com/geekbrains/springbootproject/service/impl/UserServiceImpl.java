package com.geekbrains.springbootproject.service.impl;

import com.geekbrains.springbootproject.entity.Role;
import com.geekbrains.springbootproject.entity.SystemUser;
import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.entity.repository.RoleRepository;
import com.geekbrains.springbootproject.entity.repository.UserRepository;
import com.geekbrains.springbootproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findByUserName(Principal principal) {
        return userRepository.findOneByUserName(principal.getName());
    }

    @Override
    @Transactional
    public boolean checkUserExisting(SystemUser theSystemUser) {
        String userName = theSystemUser.getUserName();
        log.debug("Processing registration form for: " + userName);
        User existing = userRepository.findOneByUserName(userName);
        if (existing != null) {
            log.debug("User name already exists.");
            return true;
        }
        createUser(theSystemUser);
        log.debug("Successfully created user: " + userName);
        return false;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private User createUser(SystemUser systemUser) {

        User user = new User();
        user.setUserName(systemUser.getUserName());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_EMPLOYEE")));

        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
