package com.zxy.conrtoller;

import com.alibaba.fastjson.JSON;
import com.zxy.constant.RedisConst;
import com.zxy.entity.User;
import com.zxy.service.UserService;
import com.zxy.util.Md5;
import com.zxy.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * UserController
 *
 * @author black
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${redis.user.expire}")
    private Integer userExpire;

    @PostMapping(value = "/userLogin")
    public ResponseEntity userLogin(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) throws Exception {
        String token = userService.loginUser(username, password);
        return ResponseEntity.success(token);
    }

    @GetMapping("/currentUser")
    public ResponseEntity getLoginUser(String token) throws Exception {
        String value = redisTemplate.opsForValue().get("login:token:" + token);
        if (value == null) {
            throw new Exception("用户未登录");
        }
        redisTemplate.opsForValue().set("login:token:" + token, value, userExpire, TimeUnit.SECONDS);
        return ResponseEntity.success(JSON.parseObject(value, User.class));
    }

    public ResponseEntity userAdd(@RequestParam("user") User user) {
        int flag=userService.addUser(user);
        
    }
}
