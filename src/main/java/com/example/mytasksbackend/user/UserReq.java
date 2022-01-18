package com.example.mytasksbackend.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserReq {
    @NotNull @NotBlank @Size(min = 3, max = 100)
    private String name;
    @NotNull @Email
    private String email;
    @NotNull @NotBlank @Size(min = 6, max = 100)
    private String password;

    public UserReq() {
    }

    public UserReq(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
