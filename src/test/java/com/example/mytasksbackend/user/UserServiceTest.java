package com.example.mytasksbackend.user;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConfig userConfig;
    @InjectMocks
    private UserService userService;

    @Test
    public void saveUser() {
        User sampleUser = new User("John", "john@email.com", "123");
        when(userRepository.count()).thenReturn(3L);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(sampleUser);
        when(userConfig.getMaxNumber()).thenReturn(10);

        User userCreated = userService.save(new UserReq("John", "john@email.com", "123"));

        assertNotNull(userCreated);
        assertNotNull(userCreated.getId());
        assertNotNull(userCreated.getCreatedAt());
        assertEquals("John", userCreated.getName());
        assertEquals("john@email.com", userCreated.getEmail());
        assertEquals("123", userCreated.getPassword());

        verify(userRepository, times(1)).save(any(User.class));
        verify(userConfig, times(1)).getMaxNumber();
    }

    @Test
    public void saveUserAlreadyExists() {
        when(userRepository.count()).thenReturn(3L);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);
        when(userConfig.getMaxNumber()).thenReturn(10);

        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> userService.save(new UserReq("John", "john@email.com", "123"))
        );

        assertNotNull(exception);
    }

}
