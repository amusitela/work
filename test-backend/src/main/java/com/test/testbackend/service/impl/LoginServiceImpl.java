package com.test.testbackend.service.impl;

import com.test.testbackend.mapper.LoginMapper;
import com.test.testbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Boolean comparePwd(Long id,String pwd) {
        if(pwd.equals(loginMapper.getPwd(id))){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Long getId(Long phone) {
        return loginMapper.getId(phone);
    }
}
