package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-18
 */

public interface UserMapper {

    /**
     * 完全基于注解配置
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    @Results({
            @Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
            @Result(property = "password", column = "password", jdbcType = JdbcType.VARCHAR)
    })
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 注解与XML结合
     * @param id
     * @return
     * @Description 使用xml中配置的resultMap作为返回对象
     */
    @Select("select * from user where id = #{id}")
    @ResultMap("baseResultMap")
    User getUserById(Integer id);

    /**
     * xml 方式配置
     * @param username
     * @return
     */
    User getUserByUsername(String username);


    /**
     * 注解方式 update
     * @param username
     * @param id
     * @return
     */
    @Update("update user set username = #{username} where id = #{id}")
    int updateUsernameById(@Param("username") String username, @Param("id") Integer id);

    /**
     * 注解方式 insert
     * @param username
     * @param password
     * @return
     */
    @Insert("insert user set username = #{username}, password = #{password}")
    int insertUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Delete("delete from user where id = #{id}")
    int deleteUserById(@Param("id") Integer id);
}
