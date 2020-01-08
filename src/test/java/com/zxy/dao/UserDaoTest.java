package com.zxy.dao;

import com.zxy.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void findByName() {
        User user = userDao.loginUser("admin", "123456");
        System.out.println(user);
    }


}