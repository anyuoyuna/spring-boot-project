package com.geekbrains.springbootproject.repository;

import com.geekbrains.springbootproject.entity.User;
import com.geekbrains.springbootproject.entity.repository.UserRepository;
import com.geekbrains.springbootproject.utils.RandomDataGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.geekbrains.springbootproject.utils.RandomDataGenerator.generateLong;
import static com.geekbrains.springbootproject.utils.RandomDataGenerator.generateString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource("/database.properties")
@TestPropertySource("/database.properties")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = generateUser();
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
    }

    private User generateUser() {
        User user = new User();
        user.setId(generateLong());
        user.setEmail(generateString());
        user.setPassword(generateString());
        user.setFirstName(generateString());
        user.setLastName(generateString());
        return user;
    }
}
