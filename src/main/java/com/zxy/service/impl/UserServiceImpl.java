package com.zxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxy.constant.RedisConst;
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
import java.util.regex.Pattern;

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

    @Value("${LoginUser.user.password}")
    private Integer userPassoword;

    @Value("${LoginUser.user.phone}")
    private Integer userPhone;


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

        User findUser = userDao.findByName(user.getName());

        int flag = 0;
        flag = userDao.addUser(user);

        // 1.判断用户名
        if (findUser.getName() == user.getName()) {
            flag = 0;
            throw new HotalBusinessException("用户名已存在");
        }

        // 2.判断用户密码长度
        if (user.getPassword().length() < userPassoword) {
            flag = 0;
            throw new HotalBusinessException("密码必须大于等于6位数");
        }

        // 3.判断用户的手机号长度
        if (user.getPhone().length() < userPhone) {
            flag = 0;
            throw new HotalBusinessException("手机号码长度必须大于等于6位数");
        }
        // 3.1使用正则表达式验证手机号码格式是否正确
        if (Reg.regexPhone(user.getPhone())) {
            flag = 0;
            throw new HotalBusinessException("手机号码格式不正确");
        }

        // 4.判断用户的邮箱是否为空
        if (user.getEmail().length() != 0 && "".equals(user.getEmail()) && user.getEmail() != null) {
            flag = 0;
            throw new HotalBusinessException("邮箱不能为空");
        }
        // 4.1使用正则表达式验证用户的邮箱是否正确
        if (Reg.regexPhone(user.getPhone())) {
            flag = 0;
            throw new HotalBusinessException("邮箱格式不正确");
        }

        return flag;
    }


}

