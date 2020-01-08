package com.zxy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author black
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 账号
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机
     */
    private String phone;
    /**
     * 用户状态 0激活，1封禁
     */
    private Integer status;
}

// 1. alt + 鼠标左键 往下拉 选中多列
// 2. ctrl + shift + →  往右选中一个单词或空格
// 3. ctrl + c 复制
// 4. ctrl + v 复制




