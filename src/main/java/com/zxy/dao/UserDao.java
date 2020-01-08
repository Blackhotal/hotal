package com.zxy.dao;

import com.zxy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author black
 * User持久层
 */
@Mapper
public interface UserDao {
    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @return User
     */
    User loginUser(@Param("name") String name, @Param("password") String password);

    /**
     * 根据用户名获取用户
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int addUser(User user);
}
