package com.test.testbackend.entity;

import lombok.Data;

@Data
public class LoginRequest {
    public  String password;
    public Long account;

    // getters and setters
}
