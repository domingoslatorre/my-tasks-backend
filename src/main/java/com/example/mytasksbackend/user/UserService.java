package com.example.mytasksbackend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceI {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private UserConfig userConfig;

    public UserService(UserRepository userRepository, UserConfig userConfig) {
        this.userRepository = userRepository;
        this.userConfig = userConfig;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(UserReq userReq) {
        if(userConfig.getMaxNumber() == userRepository.count()) {
            logger.error("Max number of users ({}) reached ", userConfig.getMaxNumber());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "não é possível cadastrar mais usuários");
        }

        if(userRepository.existsByEmail(userReq.getEmail())) {
            logger.error("User already exists");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "usuário já cadastrado com esse e-mail");
        }

        return userRepository.save(new User(userReq.getName(), userReq.getEmail(), userReq.getPassword()));
    }
}
