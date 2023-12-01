package com.test.testbackend.service;

import org.springframework.stereotype.Service;

import java.awt.*;


public interface LoginService {
    Boolean comparePwd(Long id,String pwd);
    Long getId(Long phone);
}
