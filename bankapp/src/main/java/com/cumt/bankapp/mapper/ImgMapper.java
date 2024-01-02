package com.cumt.bankapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ImgMapper {
    @Select("SELECT file FROM file where id=#{id}")
    byte[] getFiles(String id);
}
