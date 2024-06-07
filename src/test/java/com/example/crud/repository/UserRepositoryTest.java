package com.example.crud.repository;

import com.example.crud.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        // Given
        User user = new User("John");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertTrue(savedUser.getId() > 0);
        assertEquals("John", savedUser.getName());
    }

    @Test
    void testFindById() {
        // Given
        User user = new User("John");
        User savedUser = userRepository.save(user);

        // When
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());

        // Then
        assertTrue(optionalUser.isPresent());
        assertEquals("John", optionalUser.get().getName());
    }

    @Test
    void testFindAll() {
        // Given
        User user1 = new User("John");
        User user2 = new User("Jane");
        userRepository.save(user1);
        userRepository.save(user2);

        // When
        List<User> users = userRepository.findAll();

        // Then
        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jane", users.get(1).getName());
    }

    @Test
    void testDeleteById() {
        // Given
        User user = new User("John");
        User savedUser = userRepository.save(user);

        // When
        userRepository.deleteById(savedUser.getId());

        // Then
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
        assertTrue(optionalUser.isEmpty());
    }
}


