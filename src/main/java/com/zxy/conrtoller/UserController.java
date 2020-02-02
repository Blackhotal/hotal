package com.zxy.conrtoller;

import com.alibaba.fastjson.JSON;
import com.zxy.entity.User;
import com.zxy.service.UserService;
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

    @GetMapping("/userAdd")
    public ResponseEntity userAdd(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone) {
        User user = User.builder()
                .name(name)
                .password(password)
                .email(email)
                .phone(phone)
                .build();
        int flag = userService.addUser(user);
        if (flag == 1) {
            return ResponseEntity.success();
        } else {
            return ResponseEntity.error(500, "用户注册失败");
        }
    }
}



