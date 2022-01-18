package com.example.mytasksbackend.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userNotExistsByEmail() {
        User user1 = new User("John", "john@email.com", "123");
        User user2 = new User("Mary", "mary@email.com", "123");

        entityManager.persist(user1);
        entityManager.persist(user2);

        boolean userExists = userRepository.existsByEmail("paul@email.com");

        assertFalse(userExists);
    }

    @Test
    public void userExistsByEmail() {
        User user1 = new User("John", "john@email.com", "123");
        User user2 = new User("Mary", "mary@email.com", "123");
        entityManager.persist(user1);
        entityManager.persist(user2);

        boolean johnExists = userRepository.existsByEmail("john@email.com");
        boolean maryExists = userRepository.existsByEmail("mary@email.com");

        assertTrue(johnExists);
        assertTrue(maryExists);
    }



}
