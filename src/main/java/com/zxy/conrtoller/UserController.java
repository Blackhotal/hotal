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

// 单例       节约内存
// 工厂       统一创建，数据库连接，
// 代理
// 观察者     监听
// 享元模式 Integer.cache
// 装饰器     增强功能，加 buffer
// 适配器     转接口
// 策略模式   负载均衡：轮询/权重/最久没有调用/xxxx


// 1.Java基础：Java特性，多线程，JVM，BIO/NIO，异常
// 2. HTTP，计算机网络：状态码，三次握手/四次挥手，TCP/UDP，报文
// 3.Java框架：AOP和IOC分别是什么，Spring演变，Spring模块有哪些，Spring源码，
//             MyBatis原理，动态代理，自动拼接SQL语句然后执行返回
//             SpringMVC servlet -> handlerMapper ->resolver
// 4.数据库，索引，事务隔离的特性和传播性，B树，B+树，红黑树
// 5.数据结构，算法题
// 4.微服务

// 社招(经验，唬人)  /   校招(算法)
// 培训班3年，2会计，14k

// 外包 -> 甲方 驻场开发


