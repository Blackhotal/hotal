package com.zxy.service;


import com.zxy.entity.User;

/**
 * UserService
 *
 * @author black
 */

public interface UserService {
    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @return User
     */
    String loginUser(String name, String password);

    /**
     * 用户注册
     *
     * @param user
     * @return int
     */
    int addUser(User user);
}
