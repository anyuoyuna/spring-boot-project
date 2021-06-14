package com.geekbrains.springbootproject.service;

import com.geekbrains.springbootproject.entity.SystemUser;
import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.entity.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void addUserFailTest() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("Michael");

        doReturn(new User()).when(userRepository).findOneByUserName("Michael");

        assertFalse(userService.checkUserExisting(systemUser));
        verify(userRepository, times(0)).save(any(User.class));
    }
}
