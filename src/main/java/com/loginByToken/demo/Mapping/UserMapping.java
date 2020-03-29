package com.loginByToken.demo.Mapping;

import com.loginByToken.demo.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapping {

    @Select("select * from user where id = #{userId}")
    User findUserById(@Param(value = "userId") String userId);

    @Select("select * from user where username = #{username}")
    User findUserUsername(@Param(value = "username") String username);

}
