package com.zxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxy.dao.UserDao;
import com.zxy.entity.User;
import com.zxy.exception.HotalBusinessException;
import com.zxy.service.UserService;
import com.zxy.util.Md5;
import com.zxy.util.Reg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceImpl
 *
 * @author black
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${redis.user.expire}")
    private Integer userExpire;

    @Value("${loginUser.user.password.length.min}")
    private Integer USER_PASSWORD_LENGTH_MIN;

    @Value("${loginUser.user.password.length.max}")
    private Integer USER_PASSWORD_LENGTH_MAX;

    @Value("${loginUser.user.phone.length}")
    private Integer USER_PHONE_LENGTH;


    @Override
    public String loginUser(String name, String password) throws HotalBusinessException {

        // 1.查询用户是否存在
        User user = userDao.findByName(name);
        if (null == user) {
            throw new HotalBusinessException("用户不存在");
        }

        // 2.判断密码
        if (!Objects.equals(user.getPassword(), password)) {
            throw new HotalBusinessException("密码错误");
        }

        // 3.密码正确，生成token，存redis
        String token = Md5.hashKeyForDisk(UUID.randomUUID().toString().replaceAll("-", ""));
        redisTemplate.opsForValue().set("login:token:" + token, JSON.toJSONString(user), userExpire, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public int addUser(User user) {
        // 1.判断用户密码长度
        if (user.getPassword().length() < USER_PASSWORD_LENGTH_MIN || user.getPassword().length() > USER_PASSWORD_LENGTH_MAX) {
            throw new HotalBusinessException("密码长度必须为6-12位");
        }

        // 2.判断用户的手机号长度
        if (user.getPhone().length() != USER_PHONE_LENGTH) {
            throw new HotalBusinessException("手机号码长度必须为11位");
        }

        // 3使用正则表达式验证手机号码格式是否正确
        if (!Reg.regexPhone(user.getPhone())) {
            throw new HotalBusinessException("手机号码格式不正确");
        }

        // 4.使用正则表达式验证用户的邮箱是否正确
        if (!Reg.regexPhone(user.getPhone())) {
            throw new HotalBusinessException("邮箱格式不正确");
        }

        // 5.查询用户名是否存在
        User findUser = userDao.findByName(user.getName());
        if (findUser != null) {
            throw new HotalBusinessException("用户名已存在");
        }

        // 6.添加用户
        return userDao.addUser(user);
    }


}

