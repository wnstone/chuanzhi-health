package com.itheima.service;

import com.itheima.pojo.User;

/**
 * 用户接口
 */
public interface UserService {
    //根据用户名查找用户
    public User findByUsername(String username);
}
