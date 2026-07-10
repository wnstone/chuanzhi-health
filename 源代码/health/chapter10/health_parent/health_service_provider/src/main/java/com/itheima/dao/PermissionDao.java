package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

/**
 *持久层PermissionDao接口
 */
public interface PermissionDao {
    //根据角色id查找用户拥有的权限
    public Set<Permission> findByRoleId(Integer roleId);
}
