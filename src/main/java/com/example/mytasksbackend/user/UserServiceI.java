package com.example.mytasksbackend.user;

import java.util.List;

public interface UserServiceI {
    List<User> findAll();
    User save(UserReq userReq);
}
