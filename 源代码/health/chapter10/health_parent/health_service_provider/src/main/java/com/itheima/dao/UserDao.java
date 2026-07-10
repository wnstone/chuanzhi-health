package com.itheima.dao;

import com.itheima.pojo.User;

/**
 *持久层UserDao接口
 */
public interface UserDao {
    public User findByUsername(String username);//根据用户名查找用户
}
