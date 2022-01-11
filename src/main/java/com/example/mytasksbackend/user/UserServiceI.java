package com.example.mytasksbackend.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceI {
    List<User> findAll();
    Optional<User> findById(UUID id);
    User save(UserReq userReq);
}
