package com.test.testbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select pwd from user_information where phone=#{phone}")
    String getPwd(Long phone);
    @Select("select id from user_information where phone=#{phone}")
    Long getId(Long phone);
}
