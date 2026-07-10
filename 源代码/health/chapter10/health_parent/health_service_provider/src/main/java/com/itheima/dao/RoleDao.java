package com.itheima.dao;

import com.itheima.pojo.Role;

import java.util.Set;

/**
 *持久层RoleDao接口
 */
public interface RoleDao {
    //根据用户id查找用户拥有的角色
    public Set<Role> findByUserId(Integer userId);
}
